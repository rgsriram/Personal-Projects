# -*- coding: utf-8 -*-
from __future__ import unicode_literals

import logging

from django.conf import settings
from rest_framework.response import Response
from django.shortcuts import redirect
from rest_framework.decorators import api_view

from url.models import URL
from utils.helpers import hash, domain, is_valid_url
from utils.api.exceptions import InvalidURLException, URLNotFoundException, BadRequestException

logger = logging.getLogger('default')


@api_view(['POST'])
def short_url(request):
    """
    1. Find the base 62 encoded string of the given url after hashing.
    2. Saving into the db and sending response back.
    """
    long_url = request.data.get('long_url')

    if not long_url or not isinstance(long_url, unicode):
        # 'long_url' needs to be present and needs to be string.
        raise BadRequestException()

    if is_valid_url(long_url):
        short_url = "%s/%s" % (settings.APP_URL, hash(long_url))
        _domain = domain(long_url)

        URL.objects.create(
            long_url=long_url,
            short_url=short_url,
            domain=_domain
        )

        resp = dict(
            short_url=short_url,
            status='OK',
            status_codes=[]
        )

        return Response(resp)

    logger.error('Error in finding short url for %s' % long_url)
    raise InvalidURLException()


@api_view(['POST'])
def long_url(request):
    short_url = request.data.get('short_url')

    if not short_url or not isinstance(short_url, unicode):
        # 'short_url' needs to be present and needs to be string.
        raise BadRequestException()

    url = URL.objects.filter(short_url=short_url).first()
    url.no_of_clicks += 1
    url.save()

    if not url:
        raise URLNotFoundException()

    return Response(dict(
        long_url=url.long_url,
        status='OK',
        status_codes=[]
    ))


@api_view(['POST'])
def short_urls(request):
    long_urls = request.data.get('long_urls')

    if not long_urls or not isinstance(long_urls, list):
        # 'long_urls' needs to be present and needs to be list .
        raise BadRequestException()

    invalid_urls = [url for url in long_urls if not is_valid_url(url)]

    if len(invalid_urls):
        logger.error('Error in finding short urls for %s' % invalid_urls)
        raise InvalidURLException(payload=invalid_urls)

    short_long_url_map = dict()

    for long_url in long_urls:
        short_url = "%s/%s" % (settings.APP_URL, hash(long_url))
        _domain = domain(long_url)

        URL.objects.create(
            long_url=long_url,
            short_url=short_url,
            domain=_domain
        )

        short_long_url_map[long_url] = short_url

    return Response(dict(
        short_urls=short_long_url_map,
        invalid_urls=[],
        status='OK',
        status_codes=[]
    ))


@api_view(['POST'])
def long_urls(request):
    short_urls = request.data.get('short_urls')

    if not short_urls or not isinstance(short_urls, list):
        # 'short_urls' needs to be present and needs to be list.
        raise BadRequestException()

    urls = URL.objects.filter(short_url__in=short_urls)

    valid_urls = list(urls.values_list('short_url'))

    invalid_urls = list(set(short_urls).difference(valid_urls))

    if len(invalid_urls):
        raise URLNotFoundException(payload=invalid_urls)

    long_short_url_map = dict()

    for url in urls:
        long_short_url_map[url.short_url] = url.long_url
        url.no_of_clicks += 1
        url.save()

    return Response(dict(
        long_urls=long_short_url_map,
        invalid_urls=[],
        status='OK',
        status_codes=[]
    ))


@api_view(['GET'])
def redirect_view(request, encoded_str):
    url = URL.objects.filter(short_url="%s/%s" % (settings.APP_URL, encoded_str)).first()

    if url:
        url.no_of_clicks += 1
        url.save()

        return redirect(url.long_url)

    logger.error('Error in redirecting short url for %s' % encoded_str)
    return URLNotFoundException()


@api_view(['POST'])
def count(request):
    short_url = request.data.get('short_url')

    if not short_url or isinstance(short_url, str):
        # 'short_url' needs to be present and needs to be string.
        raise BadRequestException()

    url = URL.objects.filter(short_url=short_url).first()

    if not url:
        raise URLNotFoundException()

    return Response(dict(
        count=url.no_of_clicks,
        status='OK',
        status_codes=[]
    ))


@api_view(['POST'])
def clean_urls(request):
    URL.objects.all().delete()

    return Response()


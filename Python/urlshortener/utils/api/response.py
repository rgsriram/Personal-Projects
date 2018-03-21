import logging
from django.http import Http404
from rest_framework.response import Response
from rest_framework import exceptions, status
from rest_framework.compat import set_rollback
from rest_framework.renderers import JSONRenderer
import sys
import traceback

from utils.api.exceptions import InvalidURLException, URLNotFoundException, BadRequestException


logger = logging.getLogger('default')


class JSendRenderer(JSONRenderer):
    """
    Custom DRF renderer to conform to the JSend spec
    https://labs.omniti.com/labs/jsend
    """

    def render(self, data, accepted_media_type=None, renderer_context=None):
        if not data or 'data' not in data:
            if data is None:
                data = {}

            resp = data
        else:
            # formatting has already been done so return as it is
            resp = data

        return super(JSendRenderer, self).render(resp, accepted_media_type, renderer_context)


def custom_exception_handler(exc, context):
    """
    Returns the response in the JSend format (https://labs.omniti.com/labs/jsend)
    that should be used for any given exception.
    """

    status_code = status.HTTP_200_OK
    _status = 'FAILED'
    extra = dict()

    exc_type, exc_value, exc_traceback = sys.exc_info()

    if exc_type:
        type_str = exc_type.__name__
    else:
        type_str = 'Server Error'

    if isinstance(exc, InvalidURLException):
        message = exc.detail
        status_code = exc.status_code

        print exc.payload
        if exc.payload:
            extra = dict(
                invalid_urls=exc.payload
            )

    elif isinstance(exc, URLNotFoundException):
        message = exc.detail
        status_code = exc.status_code

        if exc.payload:
            extra = dict(
                invalid_urls=exc.payload
            )

    elif isinstance(exc, Http404) or isinstance(exc, BadRequestException):
        message = '%s: %s' % (type_str, str(exc))

    else:
        traceback.print_tb(exc_traceback)
        message = '%s: %s' % (type_str, str(exc))
        status_code = status.HTTP_500_INTERNAL_SERVER_ERROR

        logger.error(traceback.format_exc(exc_traceback))

    set_rollback()

    data = {
        'status': _status,
        'status_codes': [message]
    }

    if extra:
        data.update(extra)

    return Response(data, status=status_code)

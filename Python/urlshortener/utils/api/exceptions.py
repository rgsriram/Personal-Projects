from rest_framework.exceptions import APIException


class InvalidURLException(APIException):
    status_code = 400
    detail = 'INVALID_URLS'

    def __init__(self, payload=None):
        self.payload = payload


class URLNotFoundException(APIException):
    status_code = 404
    detail = 'SHORT_URLS_NOT_FOUND'

    def __init__(self, payload=None):
        self.payload = payload


class BadRequestException(APIException):
    status_code = 400
    detail = 'BAD_DATA'
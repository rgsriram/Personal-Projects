import hashlib
import string
import time
import urllib2
from urlparse import urlparse


def md5sum(str):
    m = hashlib.md5()
    salt = round(time.time())
    m.update("%s%s" % (str, salt))

    return int(m.hexdigest(), 16)


def to_base_62(num):
    alphabet = string.ascii_letters + string.digits

    if num == 0:
        return alphabet[0]

    arr = []
    base = len(alphabet)

    while num:
        num, rem = divmod(num, base)
        arr.append(alphabet[rem])
    arr.reverse()

    return ''.join(arr)


def hash(str, n=6):
    """
    Directly encoding URL to base_62 will lead into problems like reverse engineering, So randomization is introduced here with the of md5sum

    Steps:

    1. Find the md5sum of the given url with salt.
    2. Convert into base 62 encoded string. Why base 62 is we have 26 (lower case) + 26 (upper case) + 10 (digits).
    3. Taking only 6 digits coz by this we will have 62^6 possible combinations and url needs to short.
    """
    return to_base_62(md5sum(str))[0:n]


def is_valid_url(url):
    """
    Checks whether url is accessible.
    """
    try:
        urllib2.urlopen(url, timeout=2)
        return True

    except Exception:
        return False


def domain(url):
    parsed_uri = urlparse(url)
    return '{uri.scheme}://{uri.netloc}/'.format(uri=parsed_uri)

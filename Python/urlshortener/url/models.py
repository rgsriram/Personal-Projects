from django.utils import timezone
from mongoengine import *


class URL(Document):
    long_url = StringField(max_length=500)
    short_url = StringField(max_length=500)
    created_at = DateTimeField(default=timezone.now(), help_text='Url added at')
    no_of_clicks = IntField(default=0)
    domain = StringField(max_length=200, default=None, null=True)

    # In case of having expiring time for urls.
    expire_at = DateTimeField(default=None, help_text='Url expire at')
    is_purged = BooleanField(default=False)
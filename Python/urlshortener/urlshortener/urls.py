"""urlshortener URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""

import url.views

from django.conf.urls import url as u, include
from django.contrib import admin


urlpatterns = [
    u(r'^admin/', admin.site.urls),

    u(r'^(?P<encoded_str>[\w]+)/$', url.views.redirect_view),

    u(r'^fetch/', include([
        u('short-url/$', url.views.short_url),
        u('long-url/$', url.views.long_url),
        u('short-urls/$', url.views.short_urls),
        u('long-urls/$', url.views.long_urls),
        u('count/$', url.views.count),
    ])),

    u('clean-urls/$', url.views.clean_urls),
]

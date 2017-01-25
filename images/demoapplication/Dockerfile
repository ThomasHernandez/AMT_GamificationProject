FROM php:7.0-apache

MAINTAINER Ciani Antony <antony.ciani@heig-vd.ch>

COPY html/ /var/www/html/

RUN mkdir /var/www/databases

RUN touch /var/www/databases/messengerDatabase.sqlite

RUN chmod o+w /var/www/databases/

RUN chmod o+w /var/www/databases/messengerDatabase.sqlite

COPY config/php.ini /usr/local/etc/php/
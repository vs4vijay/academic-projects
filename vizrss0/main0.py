#!/usr/bin/env python


import cgi
import datetime
import wsgiref.handlers

from google.appengine.ext import db
from google.appengine.ext import webapp
from google.appengine.ext.webapp import util

from BeautifulSoup import BeautifulSoup

import urllib


class RssDB(db.Model):
  mobile = db.StringProperty()
  region = db.StringProperty()
  key0 = db.StringProperty()
  message = db.StringProperty(multiline=True)
  rss = db.StringProperty(multiline=True)
  date = db.DateTimeProperty(auto_now_add=True)


class MainHandler(webapp.RequestHandler):
    def get(self):
        
		
		
		message = self.request.get('message')
		key0 = self.request.get('key')
		region = self.request.get('region')
		mobile = self.request.get('mobile')
		
		
		if message == "" : message = "slashdot.org"
		
		
		self.response.out.write('<html><title>Vizzy</title><body>Vizzay! <BR>')
		self.response.out.write( message + " <=message<BR>")
		self.response.out.write( key0 + " <=key<BR>" )
		self.response.out.write( region + " <=region<BR>" )
		self.response.out.write( mobile + " <=mobile<BR></body></html>" )
		
		page = urllib.urlopen("http://" + message)
		data = page.read()
		
		#page = urllib.urlopen("http://www.slashdot.org")

		#page = open("lf.htm")

		soup = BeautifulSoup(page)
		#link = soup.find('link', type='application/rss+xml')
		
		link = soup.find('link', rel='alternate')
		
		
		if link == "" or link == None:
			rss = " Not Found ... ;("
		else:	
			rss = link['href']
		
		if rss == "" or rss == None : rss = " Not Found ... ;("
		
		self.response.out.write('RSS Feed '+ rss + '<BR>')
		print rss
		
		
		#self.response.out.write(data)
		
		rssdb = RssDB()
		rssdb.mobile = mobile
		rssdb.key0 = key0
		rssdb.region = region
		rssdb.message = message
		rssdb.rss = rss
		
		rssdb.put()

def main():
    application = webapp.WSGIApplication([('/rss.viz', MainHandler)],
                                         debug=True)
    util.run_wsgi_app(application)


if __name__ == '__main__':
    main()

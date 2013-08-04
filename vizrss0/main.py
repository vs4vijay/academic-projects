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
		
		
		if message == "" : message = "securitytube.net"
		
		
		link = Find(message)
		
		# print "LINEKDDDD" + link
		
		rss = link
		
		if link == "" or link == None:
			rss = " Not Found ... ;("
		self.response.out.write('<html>\n')
		self.response.out.write('<body>\n')
		
		self.response.out.write('Vizzay! \n')
		self.response.out.write( message + " <=message <br>\n")
		self.response.out.write( key0 + " <=key <br>\n" )
		self.response.out.write( region + " <=region <br>\n" )
		self.response.out.write( mobile + " <=mobile <br>\n" )
		
	
		self.response.out.write('RSS Feed : '+ rss + '\n')
		#print rss
		
		self.response.out.write('</body>\n')
		self.response.out.write('</html>\n')

		
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
	
def Find(url):

	page = urllib.urlopen("http://" + url)
	soup = BeautifulSoup(page)
	
	
	a = soup.findAll('a')
	

	#print "Length : " + str(a.__len__()) + "\n"
	
	all = ""
	for i in range(a.__len__()):
		j = 0
		t = a.pop()
		#print str(t) + "<br>"
        #ts = t.string
		
		ts = str(t)
		
		#print t.name
		
		if(ts != None):	
			#print "f"
			if(ts.find('RSS') != -1 or ts.find('rss') != -1 or ts.find('Rss') != -1):
				rss = t.get('href')
				if (rss.find("?utm_source=") == -1 and rss.startswith("http://") == True ):
					all = all + rss + "<br>" + str(j)
					return t.get('href')
		#all.strip()
		#print all
		#return all
	#a = soup.findAll('a')

	#return str(a.pop().string)

if __name__ == '__main__':
    main()

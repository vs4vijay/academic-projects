import urllib

from BeautifulSoup import BeautifulSoup

#page = urllib.urlopen("http://www.slashdot.org")

page = open("lf.htm")

soup = BeautifulSoup(page)


link = soup.find('link', type='application/rss+xml')

print link['href']
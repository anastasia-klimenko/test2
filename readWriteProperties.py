import csv
import operator

def parse_xml(xmlfilename):
    import xml.etree.ElementTree as ET

    tree = ET.parse(xmlfilename)
    root = tree.getroot()
    appName = root.findall(".//{http://www.w3.org/2001/XMLSchema}App")[0].get('name')
    user = root.findall(".//{http://www.w3.org/2001/XMLSchema}user")[0].text
    password = root.findall(".//{http://www.w3.org/2001/XMLSchema}password")[0].text
    host = root.findall(".//{http://www.w3.org/2001/XMLSchema}host")[0].text
    port = root.findall(".//{http://www.w3.org/2001/XMLSchema}port")[0].text
    dbname = root.findall(".//{http://www.w3.org/2001/XMLSchema}database")[0].text
    envProduction = 'false'
    if root.findall(".//{http://www.w3.org/2001/XMLSchema}env")[0].text == 'production':
	    envProduction = 'true'
    dburl = 'jdbc:postgresql://' + host + ':' + port + '/' + dbname
    print ('Retrieved data from XML:')
    print ('Url', dburl)
    print ('Username', user)
    print ('jss.db.pwd', password)
    print ('jss.prod', envProduction)
    herokuGitUrl = 'git@heroku.com:' + appName + '.git'
    print ('Heroku Git Url:', herokuGitUrl)
    propArr = {'jss.db.url':dburl, 'jss.db.user':user, 'jss.db.pwd':password, 'jss.prod':envProduction, 'herokuGitUrl':herokuGitUrl}
    print (propArr)
    return propArr

def read_properties(filename):
    result={ }
    with open(filename, "rt", encoding='utf8') as csvfile:
        reader = csv.reader(csvfile, delimiter='=', escapechar='', quoting=csv.QUOTE_NONE)
        for row in reader:
            if len(row) == 2:
                result[row[0]] = row[1] 
            if len(row) > 2:
                pos = row[1]
                for i in range(2, len(row)):
                    pos = pos + '=' + row[i]
                result[row[0]] = pos
    return result

def write_properties(filename,dictionary, configArr):
    with open(filename, "w") as filetowrite:
        for key, value in sorted(dictionary.items(), key=operator.itemgetter(0)):
            if key.startswith('#')==False:
                if key in configArr:
                    filetowrite.write(key + '=' + configArr[key] + '\n')
                else:
                    filetowrite.write(key + '=' + value + '\n')

def main():
    prop=parse_xml('test.xml')
    filename = 'snow.properties'
    data=read_properties(filename)
    print ("Read in: ")
    print (data.items())
    print
    write_properties(filename, data, prop)

if __name__ == '__main__': 
     main() 
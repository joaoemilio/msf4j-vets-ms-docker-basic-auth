import httplib, urllib

import requests
import base64
import json
import os

#supress TLS related warning.
requests.packages.urllib3.disable_warnings()

def base64_url_decode(inp):
    padding_factor = (4 - len(inp) % 4) % 4
    inp += "="*padding_factor
    return base64.b64decode(unicode(inp).translate(dict(zip(map(ord, u'-_'), u'+/'))))


call_api = os.environ["CALL_API"]
#url = os.environ["SERVER_URL"] + "/token"
#url = "https://gateway.mywso2.com:8243/token"
url = "https://localhost:8243/token"
#clientid=os.environ["CLIENTID"]
clientid="Ofckxh2HxiWV4XGUVoxFpU4n9H0a"
secret="f1o6RyL85AWGEf8Gbe8ZMRDGRBMa"
#secret=os.environ["SECRET"]
headers={'Authorization': 'Basic ' + base64.b64encode(clientid+':'+secret),'Content-Type': 'application/x-www-form-urlencoded'}
#scope=os.environ['SCOPE']
scope="openid scope_gerente"
#data = 'grant_type=client_credentials&scope='+scope
data = 'username=joao&password=joao1234&grant_type=password'

response = requests.post(url, data=data,headers=headers,verify=False)

if (response.status_code == 200):
   json_data=json.loads(response.text)
   if (scope == 'openid'):
       jwt=json_data['id_token']
       jwt = jwt.split('.')
       print('JWT header: ' + base64_url_decode(jwt[0]))
       print('JWT body: ' + base64_url_decode(jwt[1]))

   print('Access Token: ' + json_data['access_token'])
   print('Scope(s): ' + json_data['scope'])
   print('Expires In: ' + str(json_data['expires_in']))
   print('Token Type: ' + json_data['token_type'])

   if (call_api == 'true'):
       api_ep = os.environ["API_EP"]
       api_headers = {'Authorization': 'Bearer ' + access_token}
       response = requests.get(api_ep,headers=api_headers,verify=False)
       print(response.text)
else:
   print(response.text)

import requests

x = requests.get("http://example.com:PORT/" + "226d5055-3e41-44f4-a527-ecbce4a6bb28" + "/player_online", headers={"token":"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx"})

print(x.text)

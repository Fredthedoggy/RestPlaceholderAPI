## What Is RestPlaceholderAPI?
RestPlaceholderAPI (RestPAPI) is a small lightweight plugin, that allows you to easily parse placeholders from an external application, like a Discord bot, or Forums.

> :warning: **Warning:** RestPAPI Does nothing by itself, it just allows for external applications to parse placeholders via a simple Rest (http) API

### Spigot Page / Downloads
https://www.spigotmc.org/resources/rest-placeholderapi.90266/

### How Does it Work?
RestPAPI parses a specific placeholder, as a specific player when you make a Get request to your server, in the format:
http://backend.ip: port/<player-uuid>/<placeholder-without-%>, so an example would be http://example.com:8080/da8a8993-adfa-4d29-99b1-9d0f62fbb78d/player_name (returns json containing "Fredthedoggy")

### Security:
RestPAPI has a List of "Tokens" in the config (It starts with 2 randomly generated Java UUIDs, but can be changed). You must send the header "Token" with the value of one of the tokens in the config, or you will get a 401 (unauthorized) message.

### Plugin Support:
While it supports placeholderAPI, allowing it to support most PlaceholderAPI supported plugins, some placeholders will return an empty string, due to the fact that they cannot parse as an offline player, but will work when the player is online

Example Responses:

```json
{"status":"401","message":"Unauthorized"}
```
Without Authorization Header

```json
{"status":"404","message":"Invalid URI"}
```
With The Wrong URL Scheme

```json
{"status": "200", "message":"Fredthedoggy"}
```
Valid Placeholder (%player_name%)

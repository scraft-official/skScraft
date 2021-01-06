# skScraft - BungeeCord syntaxes for Skript
skScraft - Easy to use BungeeCord Syntaxes for Skript. 
# INSTALLATION
To use skScraft you need to install some dependencies. **Skript** of course is the main one, but to make skScraft connect and communicate with **BungeeCord**, you need to upload **BungeeBridge** plugin on your **Spigot server**, and **BungeeCord Proxy**.

1. Download **[latest](https://github.com/scraft-official/skScraft/releases/tag/1.0 "Click here to download, latest release!")** release of **skScraft**.
2. Download **[latest](https://www.spigotmc.org/resources/bungeebridge.5820/ "Click here to download, latest release!")** release of **BungeeBridge**.
3. Unzip downloaded **BungeeBridge.zip** and upload **BungeeBridge_Client** to **spigot** server, and **BungeeBridge_Server** to **BungeeCord** Proxy.
4. Upload downloaded **skScraft** to **ONLY** **spigot** server.
5. Launch **BungeeCord** proxy first, then **Spigot** server, and *change config* file of *BungeeBridge* if you need. (If you have different IP adresses of BungeeCord proxy and Spigot server in example.)
6. If you want to see some **additional information** (*about why you have <none>, when you use one of syntaxes*) go to **skScraft directory**, and set **DEBUG MODE** to true in **config.yml**. Then go back to the server and type command **/skScraft reload** in console or as a player.

------------

***Very important!** When you will use **skScraft** syntaxes, use** parasing player as text** (ex. "%player%", "%arg-1%"). The main reason of that, is parasing online player by skript, can not work correctly, if player is not connected to the same server where the syntax is used. So **always** parse **player** **as** **text**, to make it **working** without problems!

***NOT CORRECT*** -> ( *if player is connected to bungeecord network* )

***CORRECT*** -> ( *if "%player%" is connected to bungeecord network* )

------------


# SYNTAXES
#### CONDITIONS　

```java
[player] %string% is connected to [bungeecord] network
server %string% is online
server %string% is offline
```
#### EFFECTS　

```java
broadcast bungee[cord] message %string%
send bungee[cord] message %string% to [player] %string%
(send|connect) [player] %string% to bungee[cord] server %string%
```

#### EXPRESSIONS　

```java
[bungee[cord]] server of [player] %string%
number of players on [bungee[cord]] network
number of players on [bungee[cord]] server %string%
```
* [] - You don't need to use text inside of this characters (ex. [bungee[cord]] -> use bungeecord or bungee)

* () - You can choose wich text you want to use (ex. (send|connect) -> use send or connect)

------------
#### DO YOU HAVE ANY SUGESTIONS FOR OTHER SYNTAX?　
If you want me to add more **bungeecord syntaxes** to this addon, join to my **discord server**, and add your proposition on channel **suggestions**.

#### DO YOU HAVE ANY ISSUES?
If you found any bugs or issue when using my addon, fell free to join to skScraft discord server. I will try do to my best to help you, and find the solution. ;).

**skScraft Discord link ->** https://discord.gg/invite/DE4Tqr6CDD
**My Discord ID ->** 🚀 Scraft Official 🚀#9999

------------
#### EXAMPLE OF SYNTAXES:
```java
command /test:
  trigger:
    send ""
    send "&eskScraft &e» &6TO SEE THE REAL MAGIC IN CONSOLE:"
    send "&eskScraft &e» &6SET &dDEBUG MODE &6TO &dTRUE &6IN CONFIG"
    send ""

    loop 5 times:
      send "&eskScraft &e» &cStarting in &d%6-loop-number%&6."
      wait 20 ticks

    #CONDITIONS
    #Check if player is connected to network, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    if "%player%" is connected to bungeecord network:
      send "&eskScraft &e» &6You are connected!"
    else:
      send "&eskScraft &e» &6Hmmmm, there is an issue! Is bungeecord connected?"

    wait 60 ticks

    if "scraft_official" is connected to bungeecord network:
      send "&eskScraft &e» &6Oh! Scraft is on your server?!"
    else:
      send "&eskScraft &e» &6Scraft_official is offline!"
      send "&eskScraft &e» &6You can send him pm to not be alone :P"

    wait 60 ticks
    #Check if server is ONline, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    if server "servername2" is online:
      send "&eskScraft &e» &6Servername2 is online!"
    else:
      send "&eskScraft &e» &6Servername2 is offline!"

    wait 60 ticks
    #Check if server is OFFline, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    set {_server} to bungeecord server of "%player%"
    if server {_server} is offline:
      send "&eskScraft &e» &6Your server %{_server}% is offline! Wait, what????? How did you run the command???"
    else:
      send "&eskScraft &e» &6Nope! Your server %{_server}%&6 is online!"

    wait 60 ticks

    #EXPRESSIONS
    #Get the server of given player, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    set {_server} to bungeecord server of "%player%"
    send "&eskScraft &e» &6You are connected to %{_server}%&6!"

    wait 60 ticks

    #Get number of players on the given server.
    set {_online} to number of players on bungeecord server "servername2"
    send "&eskScraft &e» &6The server should be offline, and give <NONE>."
    send "&eskScraft &e» &6Result %{_online}%."

    wait 60 ticks

    #EXAMPLE OF GETTING NUMBER OF PLAYERS ON SERVER OF GIVEN PLAYER, REMEBER TO PUT PLAYER AS TEXT!!!
    set {_server} to bungeecord server of "%player%"
    set {_online} to number of players on bungeecord server {_server}
    send "&eskScraft &e» &6Players connected to your server: %{_online}%"

    wait 60 ticks

    #Get number of players connected to bungeecord network.
    set {_online} to number of players on bungeecord network
    send "&eskScraft &e» &6The number of connected players to network is: %{_online}%"

    wait 60 ticks
    #EFFECTS
    #Broadcast given message to all bungee cord servers.
    broadcast bungeecord message "&eskScraft &e» &6skScraft is amazing! It can broadcast messages!"
    wait 60 ticks

    #Send message to certain player, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    send bungee message "&eskScraft &e» &6It can send messages to given player!" to "%player%"
    wait 60 ticks

    #Connect player to given server, VERY IMPORTAN PUT PLAYER AS TEXT NOT AS PLAYER!!! {"%player%", "%arg-1%", ect...}
    connect player "%player%" to bungeecord server "servername2"
    send "&eskScraft &e» &6You can connect player to another server of your network, for this example it will give no results."

    #ADVANCED EXAMPLES
    #set {_online} to number of players on bungeecord server (bungeecord server of "%player%")
    #if server (bungeecord server of "%player%") is online:

    wait 120 ticks
    send ""
    send "&eskScraft &e» &6THIS WAS LAST EXAMPLE"
    send "&eskScraft &e» &6IF YOU HAVE A SUGGESTION FOR NEW FEATURE"
    send "&eskScraft &e» &6JOIN TO skScraft DISCORD SERVER:"
    send "&eskScraft &e» &dhttps://discord.gg/invite/DE4Tqr6CDD"
```

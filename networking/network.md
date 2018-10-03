- simulating http: `printf 'HEAD / HTTP/1.1\r\nHost: en.wikipedia.org\r\n\r\n' | nc en.wikipedia.org 80`
- `host www.google.com`
- more detailed than host: `dig www.google.com`
- different dns record types: A record (ipv4 address), AAAA (ipv6 address), CNAME (another dns address), ...
### How DNS works
- client asks caching server which ip dns address resolve to
- cache server can be your router or one from your isp provider or google dns servers (8.8.8.8), it resolves dns and cache it for ttl
- if dns is not in cache cache server will ask root server which has list of nameservers
- nameserver handles global top level domain like .com or .org..., it knows about lower level domain servers, which probably will have exact A record mapping for your dns

### Network stack
- HTTP: resources/urls/verbs/cookies...
- TCP: ports/sessions/stream sockets
- IP: ip addresses/packets
- Ethernet/Wifi: access points/wpa passwords
// The dns module contains functions belonging to two different categories:
//
// 1) Functions that use the underlying operating system facilities to perform name resolution, and that do not necessarily perform any network communication.
const dns = require('dns');

dns.lookup('nodejs.org', (err, addresses, family) => {
  console.log('addresses:', addresses);
});

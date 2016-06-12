// The crypto module provides cryptographic functionality that
// includes a set of wrappers for OpenSSL's hash, HMAC, cipher,
// decipher, sign and verify functions.
const crypto = require('crypto');

const secret = 'abcdefg';
const hash = crypto.createHmac('sha256', secret)
	.update('I love cupcakes')
	.digest('hex');
console.log(hash);

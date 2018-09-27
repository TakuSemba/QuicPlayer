# What's QUIC

QUIC (Quick UDP Internet Connections) is a new transport protocol for the internet, developed by Google.

QUIC is very similar to TCP+TLS+HTTP2, but implemented on top of UDP and has some cool features that TCP+TLS+HTTP2 does not have.

You can find more information about QUIC [here](https://www.chromium.org/quic).

This player is based off of [ExoPlayer](https://github.com/google/ExoPlayer) using QUIC extension.

# Quicstart

You would probably ready to send QUIC request, but the endpoint might not be able to handle QUIC requests.

The esiest way to build an server for QUIC so far is to use [Caddy](https://github.com/mholt/caddy). This is an open-source web server and has an Experimental QUIC support.

Caddy can be an proxy server, so you can just enable QUIC with `-quic` option and proxy your request to your existing web server.

```
https://proxy.example.com {
  proxy / http://example.com {
    header_upstream Host {host}
    header_upstream X-Forwarded-For {remote}
  }
  tls postmaster@example.com
}
```


For more information, you should check [here](https://github.com/mholt/caddy/wiki/QUIC)


# Others

Slides: xxx

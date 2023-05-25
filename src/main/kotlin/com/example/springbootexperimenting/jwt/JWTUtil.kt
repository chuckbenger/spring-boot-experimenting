package com.example.springbootexperimenting.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.security.Key
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*


@Service
class JWTUtil {
    private val SECRET_KEY = "foobar_123456789_foobar_123456789_foobar_123456789_foobar_123456789"


    fun issueToken(subject: String): String {
        return issueToken(subject, emptyMap())
    }

    fun issueToken(subject: String, vararg scopes: String): String {
        return issueToken(subject, java.util.Map.of<String, Any>("scopes", scopes))
    }

    fun issueToken(subject: String, scopes: List<String>): String {
        return issueToken(subject, java.util.Map.of<String, Any>("scopes", scopes))
    }


    fun issueToken(
        subject: String,
        claims: Map<String, Any>
    ): String {
        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuer("https://amigoscode.com")
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(
                Date.from(
                    Instant.now().plus(15, ChronoUnit.DAYS)
                )
            )
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSigningKey(): Key {
        return Keys.hmacShaKeyFor(SECRET_KEY.toByteArray())
    }

    fun isTokenValid(jwt: String, username: String): Boolean {
        val subject = getSubject(jwt)
        return subject == username && !isTokenExpired(jwt)
    }

    private fun isTokenExpired(jwt: String): Boolean {
        val today: Date = Date.from(Instant.now())
        return getClaims(jwt).getExpiration().before(today)
    }
}
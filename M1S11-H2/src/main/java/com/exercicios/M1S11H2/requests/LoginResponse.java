package com.exercicios.M1S11H2.requests;

public record LoginResponse(String jwtToken, long expireIn) {}
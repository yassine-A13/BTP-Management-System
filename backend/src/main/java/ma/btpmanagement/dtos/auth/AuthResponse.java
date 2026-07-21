package ma.btpmanagement.dtos.auth;

public record AuthResponse(String token, String tokenType, long expiresIn) {
}

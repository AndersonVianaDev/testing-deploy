package com.anderson.testing_deploy.core.exceptions;

import java.time.Instant;

public record StandardException(Instant timestamp, Integer status, String message, String path) {
}

package me.fredthedoggy.restpapi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import spark.Service;

import java.util.List;
import java.util.UUID;

import static spark.Service.ignite;

class SparkWrapper {
    Service http;

    void create(int port, List<String> tokens) {
        http = ignite().port(port);
        http.get("/:uuid/:placeholder", (request, response) -> {

            if (request.headers("token") == null) {
                response.type("application/json");
                response.status(401);
                return "{\"status\":\"401\",\"message\":\"Unauthorized\"}";
            } else if (tokens.stream().noneMatch(request.headers("token")::contains)) {
                response.type("application/json");
                response.status(401);
                return "{\"status\":\"401\",\"message\":\"Unauthorized\"}";
            } else {
                response.type("application/json");
                UUID specifiedUUID;
                try {
                    specifiedUUID = UUID.fromString(request.params(":uuid"));
                }
                catch(Exception e) {
                    response.type("application/json");
                    response.status(400);
                    return "{\"status\":\"400\",\"message\":\"Invalid UUID\"}";
                }
                if (Bukkit.getOfflinePlayer(specifiedUUID).hasPlayedBefore()) {

                    response.type("application/json");
                    response.status(200);

                    String Placeholder = "{\"status\":\"200\",\"message\":\"" + PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(UUID.fromString(request.params(":uuid"))), "%" + request.params(":placeholder") + "%") + "\"}";

                    if (Placeholder.equals("%" + request.params(":placeholder") + "%")) {

                        response.type("application/json");
                        response.status(406);
                        return "{\"status\":\"406\",\"message\":\"Invalid Placeholder\"}";

                    } else {

                        return Placeholder;

                    }
                } else {

                    response.type("application/json");
                    response.status(400);
                    return "{\"status\":\"400\",\"message\":\"Player Has Not Played Before\"}";

                }
            }

        });
        http.get("/*", (request, response) -> {

            response.status(404);
            response.type("application/json");
            return "{\"status\":\"404\",\"message\":\"Invalid URI\"}";

        });
        http.get("/*/*/*", (request, response) -> {

            response.status(404);
            response.type("application/json");
            return "{\"status\":\"404\",\"message\":\"Invalid URI\"}";

        });
        http.get("/*/*/*/*", (request, response) -> {

            response.status(404);
            response.type("application/json");
            return "{\"status\":\"404\",\"message\":\"Invalid URI\"}";

        });
        http.notFound((request, response) -> {

            response.status(404);
            response.type("application/json");
            return "{\"status\":\"404\",\"message\":\"Invalid URI\"}";

        });
    }

    void destroy() {
        http.stop();
    }

}
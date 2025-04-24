package com.example.knightboard;

import com.example.knightboard.exception.DomainException;
import com.example.knightboard.factory.CommandFactory;
import com.example.knightboard.model.Board;
import com.example.knightboard.model.ExitStatus;
import com.example.knightboard.model.KnightPosition;
import com.example.knightboard.service.KnightService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.knightboard.json.JsonService.fetchJson;
import static com.example.knightboard.model.Board.toMatrix;

public class Application {

    public static void main(String[] args) throws IOException {
        ExitStatus exitStatus;
        try{
            var boardApiUrl = System.getenv("BOARD_API");
            var boardJson = fetchJson(boardApiUrl);
            var mapper = new ObjectMapper();
            var boardPojo = mapper.readValue(boardJson, Board.class);
            int[][] board = toMatrix(boardPojo);

            var knightPosition = new KnightPosition();

            var commandMapper = new ObjectMapper();
            var commandApiUrl = System.getenv("COMMANDS_API");
            var commandJson = fetchJson(commandApiUrl);
            Map<String, List<String>> map = commandMapper.readValue(commandJson, new TypeReference<>() {});
            var commands = map.get("commands");
            var commandFactory = new CommandFactory(new KnightService(knightPosition, board));
            var commandList = commands.stream()
                    .map(commandFactory::getCommand)
                    .toList();

            for(var command : commandList){
                command.doExecute();
            }

            exitStatus = new ExitStatus(knightPosition, "SUCCESS");

        } catch (DomainException e){
            exitStatus = new ExitStatus(e.getMessage());
        } catch (Exception e){
            exitStatus = new ExitStatus("GENERIC_ERROR");
        }

        printAsJson(exitStatus);

    }

    public static void printAsJson(ExitStatus exitStatus) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(exitStatus);
            System.out.println(json);
        } catch (Exception ignored) {
        }
    }

}

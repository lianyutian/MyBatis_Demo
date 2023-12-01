package com.github.lianyutian.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonParse {
    public static void main(String[] args) {
        try {
            // 从文件中读取 JSON 数据
            String filePath = "E:\\workspace\\sty_project\\mybatis\\MyBatis_Demo\\示例2\\src\\main\\resources\\test.json";
            String json = readFile(filePath);

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json);

                traverseJson(jsonNode, "", "", 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(json);
//
//                // 过滤特定字段并保持层级
//                JsonNode filteredNode = filterFieldsAndPreserveHierarchy(objectMapper, jsonNode, "name");
//
//                // 打印结果
//                System.out.println("Filtered JSON:");
//                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filteredNode));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private static void traverseJson(JsonNode jsonNode, String prefix, String key, int pre_i) {
        if (jsonNode.isObject()) {
            jsonNode.fields().forEachRemaining(entry -> {
                if (entry.getKey().equals("jobtype") || entry.getKey().equals("industry")) {
                    System.out.println(prefix + entry.getKey());
                }
                traverseJson(entry.getValue(), prefix + "  ", entry.getKey(), 0);

            });
        } else if (jsonNode.isArray()) {
            for (int i = 0; i < jsonNode.size(); i++) {
                System.out.println(prefix + "[" + pre_i + i + "]");
                traverseJson(jsonNode.get(i), prefix + "  ", "", i);
            }
        } else {
            if (key.equals("name")) {
                System.out.println(prefix + jsonNode.asText());
            }

        }
    }

    private static JsonNode filterFieldsAndPreserveHierarchy(ObjectMapper objectMapper, JsonNode jsonNode, String... fields) {
        if (jsonNode.isObject()) {
            ObjectNode filteredObject = objectMapper.createObjectNode();

            for (String field : fields) {
                JsonNode selectedNode = findNode(jsonNode, field);
                if (selectedNode != null) {
                    filteredObject.set(field, selectedNode);
                }
            }

            return filteredObject;
        } else if (jsonNode.isArray()) {
            ArrayNode filteredArray = objectMapper.createArrayNode();

            for (JsonNode arrayElement : jsonNode) {
                filteredArray.add(filterFieldsAndPreserveHierarchy(objectMapper, arrayElement, fields));
            }

            return filteredArray;
        }

        return null;
    }

    private static JsonNode findNode(JsonNode jsonNode, String fieldPath) {
        String[] fieldNames = fieldPath.split("\\.");

        for (String fieldName : fieldNames) {
            jsonNode = jsonNode.get(fieldName);
            if (jsonNode == null) {
                return null; // Field not found
            }
        }

        return jsonNode;
    }
}

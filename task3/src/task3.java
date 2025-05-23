import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
class TestNode {
    public int id;
    public String title;
    public String value;

    @JsonProperty("values")
    public List<TestNode> values;
}
class ValueEntry {
    public int id;
    public String value;
}

class ValuesContainer {
    public List<ValueEntry> values;
}


public class task3 {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Передайте 3 аргумента: путь к tests.json, values.json и report.json");
            return;
        }

        String testsPath = args[0];
        String valuesPath = args[1];
        String reportPath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        // Чтение values.json
        ValuesContainer valuesContainer = mapper.readValue(new File(valuesPath), ValuesContainer.class);
        Map<Integer, String> valueMap = new HashMap<>();
        for (ValueEntry entry : valuesContainer.values) {
            valueMap.put(entry.id, entry.value);
        }

        // Чтение tests.json
        Map<String, List<TestNode>> testsRoot = mapper.readValue(
                new File(testsPath),
                new TypeReference<>() {}
        );

        // Заполнение значений
        List<TestNode> tests = testsRoot.get("tests");
        fillValues(tests, valueMap);

        // Запись результата в report.json
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportPath), Map.of("tests", tests));
        System.out.println("Готово! Результат записан в " + reportPath);
    }

    private static void fillValues(List<TestNode> tests, Map<Integer, String> valueMap) {
        for (TestNode node : tests) {
            if (valueMap.containsKey(node.id)) {
                node.value = valueMap.get(node.id);
            }
            if (node.values != null) {
                fillValues(node.values, valueMap);
            }
        }
    }
}

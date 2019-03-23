import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private static String INVALID_NUMBER_MESSAGE = "Invalid number!";
    private static String INVALID_URL_MESSAGE = "Invalid URL!";

    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = new ArrayList<>();
        this.setNumbers(numbers);
        this.urls = new ArrayList<>();
        this.setUrls(urls);
    }

    private void setNumbers(List<String> numbers) {
        this.numbers.addAll(numbers);
    }

    private void setUrls(List<String> urls) {
        this.urls.addAll(urls);
    }

    private boolean isValidNumber(String number) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return matcher.group().equals(number);
        }
        return false;
    }

    private boolean isUrlValid(String url) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group().equals(url);
        }
        return false;
    }

    @Override
    public String call() {
        StringBuilder builder = new StringBuilder();
        for (String number : this.numbers) {
            if (isValidNumber(number)) {
                builder.append("Calling... ").append(number);
            } else {
                builder.append(Smartphone.INVALID_NUMBER_MESSAGE);
            }
            if (this.numbers.indexOf(number) < this.numbers.size() - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    @Override
    public String browse() {
        StringBuilder builder = new StringBuilder();
        for (String url : this.urls) {
            if (isUrlValid(url)) {
                builder.append("Browsing: ").append(url).append("!");
            } else {
                builder.append(Smartphone.INVALID_URL_MESSAGE);
            }
            if (this.urls.indexOf(url) < this.urls.size() - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}

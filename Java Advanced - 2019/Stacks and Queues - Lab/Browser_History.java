import java.util.ArrayDeque;
import java.util.Scanner;

public class Browser_History {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String url = scanner.nextLine();
        String currentUrl = "";
        ArrayDeque<String> browserHistory = new ArrayDeque<>();
        ArrayDeque<String> forwardsUrls = new ArrayDeque<>();

        while (!url.equals("Home")) {
            if (url.equals("back")) {
                if (browserHistory.isEmpty()) {
                    System.out.println("no previous URLs");
                    url = scanner.nextLine();
                    continue;
                } else {
                    forwardsUrls.push(currentUrl);
                    currentUrl = browserHistory.pop();
                }
            } else if (url.equals("forward")) {
                if (forwardsUrls.isEmpty()) {
                    System.out.println("no next URLs");
                    url = scanner.nextLine();
                    continue;
                } else {
                    browserHistory.push(currentUrl);
                    currentUrl = forwardsUrls.pop();
                }
            } else {
                forwardsUrls.clear();
                if (!currentUrl.equals("")) {
                    browserHistory.push(currentUrl);
                }
                currentUrl = url;
            }
            System.out.println(currentUrl);

            url = scanner.nextLine();
        }
    }
}

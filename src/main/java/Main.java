import java.util.ArrayList;
import java.util.Scanner;

class Clothing {
    protected String name;
    protected String color;
    protected String size;

    public Clothing(String name, String color, String size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    public void showInfo() {
        System.out.println("옷 이름: " + name);
        System.out.println("색상: " + color);
        System.out.println("사이즈: " + size);
    }
}

class Top extends Clothing {
    private String sleeve;

    public Top(String name, String color, String size, String sleeve) {
        super(name, color, size);
        this.sleeve = sleeve;
    }

    @Override
    public void showInfo() {
        System.out.println("[상의]");
        super.showInfo();
        System.out.println("소매: " + sleeve);
    }
}

class Bottom extends Clothing {
    private String length;

    public Bottom(String name, String color, String size, String length) {
        super(name, color, size);
        this.length = length;
    }

    @Override
    public void showInfo() {
        System.out.println("[하의]");
        super.showInfo();
        System.out.println("길이: " + length);
    }
}

class Outer extends Clothing {
    private String fastening;

    public Outer(String name, String color, String size, String fastening) {
        super(name, color, size);
        this.fastening = fastening;
    }

    @Override
    public void showInfo() {
        System.out.println("[아우터]");
        super.showInfo();
        System.out.println("잠금 방식: " + fastening);
    }
}


public class Main {
    static String inputRequiredText(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("필수값입니다.");
        }
    }

    static int inputNumber(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }
    }

    static void addClothing(ArrayList<Clothing> closet, Scanner scanner) {
        System.out.println("어떤 종류의 옷을 넣을까요?");
        System.out.println("1. 상의");
        System.out.println("2. 하의");
        System.out.println("3. 아우터");
        int type = inputNumber(scanner, "종류 선택: ");

        if (type < 1 || type > 3) {
            System.out.println("잘못된 종류입니다.");
            return;
        }

        String name = inputRequiredText(scanner, "옷 이름: ");
        String color = inputRequiredText(scanner, "색상: ");
        String size = inputRequiredText(scanner, "사이즈: ");

        if (type == 1) {
            String sleeve = inputRequiredText(scanner, "소매 종류(반팔,긴팔,없음): ");

            closet.add(new Top(name, color, size, sleeve));
            System.out.println("상의를 옷장에 넣었습니다.");

        } else if (type == 2) {
            String length = inputRequiredText(scanner, "길이(반바지,바지): ");

            closet.add(new Bottom(name, color, size, length));
            System.out.println("하의를 옷장에 넣었습니다.");

        } else {
            String fastening = inputRequiredText(scanner, "잠금 방식(지퍼/단추): ");

            closet.add(new Outer(name, color, size, fastening));
            System.out.println("아우터를 옷장에 넣었습니다.");
        }
    }

    static void showClothingListMenu(ArrayList<Clothing> closet, Scanner scanner) {
        if (closet.isEmpty()) {
            System.out.println("옷장이 비어 있습니다.");
            return;
        }

        while (true) {
            System.out.println("===== 옷 목록 =====");
            showClothingList(closet);

            System.out.println("1. 옷 꺼내기");
            System.out.println("0. 뒤로가기");

            int closetMenu = inputNumber(scanner, "선택: ");

            if (closetMenu == 1) {
                takeOutClothing(closet, scanner);
                return;

            } else if (closetMenu == 0) {
                System.out.println("메인으로 돌아갑니다.");
                return;

            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }
    }

    static void showClothingList(ArrayList<Clothing> closet) {
        for (int i = 0; i < closet.size(); i++) {
            System.out.println((i + 1) + "번째 옷");
            closet.get(i).showInfo();
            System.out.println();
        }
    }

    static void takeOutClothing(ArrayList<Clothing> closet, Scanner scanner) {
        while (true) {
            if (closet.isEmpty()) {
                System.out.println("옷장이 비어 있습니다.");
                break;
            }

            int number = inputNumber(scanner, "꺼낼 옷 번호: ");

            if (number < 1 || number > closet.size()) {
                System.out.println("옷장에 없는 번호입니다.");
                continue;
            }

            Clothing takeOutClothing = closet.remove(number - 1);
            System.out.println(takeOutClothing.name + "을(를) 꺼냈습니다.");

            if (closet.isEmpty()) {
                System.out.println("옷장이 비어 있습니다.");
                return;
            }

            System.out.println();
            System.out.println("===== 남은 옷 목록 =====");
            showClothingList(closet);

            while (true) {
                System.out.println("1. 계속 꺼내기");
                System.out.println("0. 메인으로 돌아가기");

                int afterTakeOutMenu = inputNumber(scanner, "선택: ");

                if (afterTakeOutMenu == 1) {
                    break;

                } else if (afterTakeOutMenu == 0) {
                    System.out.println("메인으로 돌아갑니다.");
                    return;

                } else {
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Clothing> closet = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("===== 오늘은 어떤 옷을 입으실 건가요? =====");
            System.out.println("1. 옷 넣기");
            System.out.println("2. 옷 목록 보기");
            System.out.println("0. 종료");

            int menu = inputNumber(scanner,"메뉴 선택: ");


            if (menu == 1) {
                addClothing(closet, scanner);

            } else if (menu == 2) {
                showClothingListMenu(closet, scanner);

            } else if (menu == 0) {
                System.out.println("옷장 문을 닫습니다.");
                break;

            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }

        scanner.close();
    }
}
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class 파일명정렬 {

    public static void main(String[] args) {
        파일명정렬 file = new 파일명정렬();
        Arrays.stream(file.solution(new String[]{"F-4", "F-5", "F-6", "F-1"})).forEach(System.out::println);
    }

    class File implements Comparable<File>{
        String head;
        String number;
        String tail;

        File(String h, String n, String t) {
            head = h;
            number = n;
            tail = t;
        }

        @Override
        public int compareTo(File o) {

            String upperHead = head.toUpperCase();
            String anotherUpperHead = o.head.toUpperCase();

            if (upperHead.compareTo(anotherUpperHead) == 0) {
                return Integer.parseInt(number) - Integer.parseInt(o.number);
            } else {
                return upperHead.compareTo(anotherUpperHead);
            }
        }

        public String toString() {
            return head + number + tail;
        }
    }

    public String[] solution(String[] files) {
        LinkedList<File> newFiles = new LinkedList<>();

        for(String file : files) {
            newFiles.add(splitFormat(file));
        }

        Collections.sort(newFiles);

        return newFiles.stream().map(File::toString).toArray(String[] :: new);
    }
    public File splitFormat(String file) {
        int numberStartIdx = 0;
        int numberEndIdx = 0;
        int tmpIdx = 0;

        for(char c : file.toCharArray()) {
            if(numberStartIdx == 0 && '0' <= c && c <= '9') {
                numberStartIdx = tmpIdx;
            } else if ('0' <= c && c <= '9'){

            }
            else {
                if (numberStartIdx != 0) {
                    numberEndIdx = tmpIdx - 1;
                    break;
                }
            }
            tmpIdx ++;
        }

        return new File(file.substring(0, numberStartIdx),
            numberEndIdx == 0? file.substring(numberStartIdx) : file.substring(numberStartIdx, numberEndIdx + 1),
            numberEndIdx == 0? "" : file.substring(numberEndIdx + 1));
    }

//head 는 사전 순
// number는 작은 순
// 이외는 입력에 주어진 순서 유지!



}

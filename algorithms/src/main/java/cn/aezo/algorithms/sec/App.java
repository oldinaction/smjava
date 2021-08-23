package cn.aezo.algorithms.sec;

public class App {
    
    /**
     * 找到最早出现bad开头的字符串
     * input: null output: null
     * input: [] output: null
     * input: ["good1", "good2", "good3", "bad1", "bad2", "bad3"] output: "bad1"
     * input: ["good1", "good2", "bad1", "bad2", "bad3"] output: "bad1"
     * input: ["good1"] output: null
     * input: ["good1", "bad1"] output: "bad1"
     */
    public static void main( String[] args ) {
        System.out.println("ret = " + findFirstBadCommit(new String[]{"good1", "bad0", "good2", "good3", "good3", "bad1", "bad2", "bad3"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"good1", "good2", "good3", "good3", "bad1", "bad2", "bad3"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"good1"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"good1", "good2"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"bad1"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"bad1", "bad2"}));
        System.out.println("ret = " + findFirstBadCommit(new String[]{"good1", "good2", "good3", "bad1", "bad2", "bad3"}));
    }

    private static String findFirstBadCommit(String[] commits) {
        if(commits == null || commits.length == 0) return null;
        int index = findBad(commits, 0, commits.length - 1);
        return index <= commits.length - 1 && isBad(commits[index]) ? commits[index] : null;
    }

    private static Integer findBad(String[] commits, int start ,int end) {
        if(start == end) {
            return isBad(commits[start]) ? start : start + 1;
        }
        if(isBad(commits[start])) {
            return start;
        }

        int mid;
        int len = end - start + 1;
        if(len % 2 == 0) {
            // 偶
            mid = len / 2;
            int left = findBad(commits, start, start + mid - 1);
            int right = findBad(commits, start + mid, end);
            return Integer.min(left, right);
        } else {
            // 奇
            mid = (len - 1) / 2;
            if(isBad(commits[mid])) {
                return findBad(commits, start, mid + start - 1);
            } else {
                return findBad(commits, mid + start + 1, end);
            }
        }
    }

    // return true if it is bad
    private static boolean isBad(String commit){
        return commit.startsWith("bad");
    }


}

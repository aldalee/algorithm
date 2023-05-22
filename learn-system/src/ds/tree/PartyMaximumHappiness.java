package ds.tree;

import java.io.*;
import java.util.*;

/**
 * https://www.nowcoder.com/practice/a5f542742fe24181b28f7d5b82e2e49a?tpId=0&tqId=0&rp=0&ru=&qru=/ta/programmer-code-interview-guide/question-ranking
 * 派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下的原则:
 *    1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 *    2.派对的整体快乐值是所有到场员工快乐值的累加
 *    3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 * 注意，提交代码时，将类名修改为Main
 * @author HuanyuLee
 * @date 2023/5/22
 */
public class PartyMaximumHappiness {
    static class Employee {
        int id;
        int happy;
        List<Employee> nexts = new ArrayList<>();

        Employee(int id, int happy) {
            this.id = id;
            this.happy = happy;
        }
    }

    static class Info {
        public int noAttend;  // 不参加party时的最大快乐值
        public int attend;    // 参加party时的最大快乐值

        public Info(int noAttend, int attend) {
            this.noAttend = noAttend;
            this.attend = attend;
        }
    }

    private static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int noAttend = 0;
        int attend = employee.happy;
        for (Employee next : employee.nexts) {
            Info nextInfo = process(next);
            noAttend += Math.max(nextInfo.noAttend, nextInfo.attend);   // employee不参加party时的最大快乐值
            attend += nextInfo.noAttend;                                // employee参加party时的最大快乐值
        }
        return new Info(noAttend, attend);
    }

    public static int partyMaxHappiness(Employee boss) {
        Info bossInfo = process(boss);
        return Math.max(bossInfo.noAttend, bossInfo.attend);
    }

    public static void main(String[] args) throws IOException {
        /* Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int root = sc.nextInt();
        HashMap<Integer, Employee> employees = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int happy = sc.nextInt();
            employees.put(i, new Employee(i, happy));
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            employees.get(u).nexts.add(employees.get(v));
        }
        sc.close();
        System.out.println(partyMaxHappiness(employees.get(root))); */

        // ==================== 下面的代码优化了IO ====================

        // 创建BufferedReader对象，用于读取输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 创建BufferedWriter对象，用于输出结果
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 读取第一行输入，包括n和root
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());           // 读取n，表示公司的总人数
        int root = Integer.parseInt(st.nextToken());        // 读取root，表示公司的老板

        HashMap<Integer, Employee> employees = new HashMap<>();
        // 读取第二行输入，包括每个员工的快乐值
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int happy = Integer.parseInt(st.nextToken());   // 读取快乐值
            employees.put(i, new Employee(i, happy));       // 将员工加入HashMap
        }

        // 读取第三行到第n-1行输入，包括每个员工的下属关系
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());       // 读取下属关系中的上级
            int v = Integer.parseInt(st.nextToken());       // 读取下属关系中的下级
            employees.get(u).nexts.add(employees.get(v));   // 将下级加入上级的nexts列表中
        }
        br.close();                                         // 关闭 BufferedReader 对象

        // 调用partyMaxHappiness方法计算最大快乐值
        int result = partyMaxHappiness(employees.get(root));
        // 输出结果
        bw.write(result + "\n");                        // 写入结果
        bw.flush();                                         // 刷新BufferedWriter对象
        bw.close();                                         // 关闭BufferedWriter对象
    }
}

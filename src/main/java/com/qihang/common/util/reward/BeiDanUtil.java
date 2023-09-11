package com.qihang.common.util.reward;

import cn.hutool.core.util.NumberUtil;
import com.qihang.controller.beidan.dto.BeiDanMatchDTO;
import com.qihang.controller.racingball.app.vo.BallCalculationVO;
import com.qihang.controller.racingball.app.vo.BallCombinationVO;
import com.qihang.controller.racingball.app.vo.BallOptimizationVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-08 18:18
 */
public class BeiDanUtil {

    /**
     * 从m个数中选n个数的排列组合
     *
     * @param m n
     * @return
     */
    static int[][] getIndexgroup(int m, int n)//m中选n个 返回索引组合
    {
        int[] a = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = i;
        }
        List<int[]> list = new ArrayList<>();
        int[] b = new int[n];
        C(m, n, a, b, list);
        int[][] rest = new int[list.size()][n];
        for (int i = 0; i < list.size(); i++) {
            int[] re = list.get(i);
            for (int j = 0; j < n; j++) {
                rest[i][j] = re[j];
            }
        }
        return rest;
    }

    /**
     * 从m个数中选n个数的排列组合 是getIndexgroup的子问题
     *
     * @param m n
     * @return
     */
    static void C(int m, int n, int[] a, int[] b, List<int[]> list) {
        int i, j;
        for (i = n; i <= m; i++) {
            b[n - 1] = i - 1;
            if (n > 1) {
                C(i - 1, n - 1, a, b, list);
            } else {
                int[] op = new int[b.length];
                for (j = 0; j <= b.length - 1; j++) {
                    op[j] = a[b[j]];
                }
                list.add(op);
            }
        }
    }

    /**
     * 获取某个分组注数
     *
     * @param basketballMatchDTOall 比赛场 groupingindex 场次索引
     * @return
     */
    public static int getbetsnum(beiDanMatchDTOall[] basketballMatchDTOall, int[] groupingindex)//获取注数
    {
        int result = 1;
        for (int i = 0; i < groupingindex.length; i++) {
            result = result * basketballMatchDTOall[groupingindex[i]].length;
        }
        return result;
    }

    /**
     * 获取所有分组总注数
     *
     * @param basketballMatchDTOall 比赛场 groupingindex 场次索引
     * @return
     */
    public static int getallbetsnum(beiDanMatchDTOall[] basketballMatchDTOall, int[][] groupingindex)//获取注数
    {
        int result = 0;
        for (int i = 0; i < groupingindex.length; i++) {
            int[] index = groupingindex[i];
            result = result + getbetsnum(basketballMatchDTOall, index);
        }
        return result;
    }

    /**
     * 获取每个分组最大最小赔率
     *
     * @param basketballMatchDTOall 比赛场 []m选取的比赛索引
     * @return
     */
    public static double[] getrange(beiDanMatchDTOall[] basketballMatchDTOall, int[] m)//获取比赛组合中最大赔率与最小赔率
    {
        double max = 1, min = 1;
        for (int i = 0; i < m.length; i++) {
            max = max * basketballMatchDTOall[m[i]].maxodds;
            min = min * basketballMatchDTOall[m[i]].minodds;
        }
        return new double[]{max, min};
    }

    /**
     * 获取所有分组最大最小赔率
     *
     * @param basketballMatchDTOall 比赛场 [][]m 所有分组比赛索引
     * @return
     */
    public static double[] getallrange(beiDanMatchDTOall[] basketballMatchDTOall, int[][] m) {
        int[] x = m[0];
        double[] result = getrange(basketballMatchDTOall, x);
        double[] zresult = new double[2];
        for (int i = 1; i < m.length; i++) {
            x = m[i];
            zresult = getrange(basketballMatchDTOall, x);
            result[0] = result[0] + zresult[0];
            if (zresult[1] < result[1]) {
                result[1] = zresult[1];
            }
        }
        return result;
    }

    /**
     * 获取比赛按所有分组方式的所有组合
     *
     * @param footballMatchList 比赛场
     * @return
     */
    public static List<List<BallCombinationVO>> getallfootballOptimization(List<BeiDanMatchDTO> footballMatchList, int[][] groupingindex) {
        List<List<BallCombinationVO>> foot = new ArrayList<>();
        for (int i = 0; i < groupingindex.length; i++) {
            int[] x = groupingindex[i];
            foot.addAll(getfootballOptimization(footballMatchList, x));
        }
        return foot;

    }

    /**
     * 获取比赛按一场分组方式的所有组合
     *
     * @param footballMatchList 比赛场
     * @return
     */
    public static List<List<BallCombinationVO>> getfootballOptimization(List<BeiDanMatchDTO> footballMatchList, int[] groupingindex) {
        List<BallCombinationVO>[] footballMatchDTOlistarry = new ArrayList[groupingindex.length];
        for (int i = 0; i < groupingindex.length; i++) {
            footballMatchDTOlistarry[i] = getAllOptions(footballMatchList.get(groupingindex[i]));
        }
        int m = footballMatchDTOlistarry.length;
        List<BallCombinationVO> footballCombinationVOList = new ArrayList<>();
        List<List<BallCombinationVO>> reust = new ArrayList<>();
        getfootballOptimization(footballMatchDTOlistarry, m, footballCombinationVOList, reust);
        return reust;
    }

    /**
     * 获取某厂比赛所有组合 递归查找
     *
     * @param footballCombinationVOList 记录本次遍历路过元素
     * @return
     */
    public static void getfootballOptimization(List<BallCombinationVO>[] footballMatchDTOlistarry, int m, List<BallCombinationVO> footballCombinationVOList, List<List<BallCombinationVO>> reust) {
        if (m == 0) {
            List<BallCombinationVO> listcopy = new ArrayList<>();
            listcopy.addAll(footballCombinationVOList);
            reust.add(listcopy);
            footballCombinationVOList.remove(footballCombinationVOList.size() - 1);
        } else {
            for (int i = 0; i < footballMatchDTOlistarry[m - 1].size(); i++) {
                footballCombinationVOList.add(footballMatchDTOlistarry[m - 1].get(i));
                getfootballOptimization(footballMatchDTOlistarry, m - 1, footballCombinationVOList, reust);
            }
            if (footballCombinationVOList.isEmpty()) {
                return;
            }
            footballCombinationVOList.remove(footballCombinationVOList.size() - 1);

        }
    }

    /**
     * 获取一场比赛某种取胜方式所有选项
     *
     * @param footballMatchDTO 比赛表
     * @return
     */
    public static List<BallCombinationVO> getOptions(BeiDanMatchDTO footballMatchDTO, List<Map<String, Object>> oddlist, String... args) {
        List<BallCombinationVO> footballCombinationVOList = new ArrayList<>();
        for (int i = 0; i < oddlist.size(); i++) {
            BallCombinationVO footballCombinationVO = new BallCombinationVO();
            footballCombinationVO.setHomeTeam(footballMatchDTO.getHomeTeam());
            footballCombinationVO.setNumber(footballMatchDTO.getNumber());
            footballCombinationVO.setVisitingTeam(footballMatchDTO.getVisitingTeam());
            if (args.length > 0) {
                footballCombinationVO.setContent("让" + oddlist.get(i).get("describe").toString() + "(" + Double.parseDouble(oddlist.get(i).get("odds").toString()) + ")");
            } else {
                footballCombinationVO.setContent(oddlist.get(i).get("describe").toString() + "(" + Double.parseDouble(oddlist.get(i).get("odds").toString()) + ")");
            }
            footballCombinationVOList.add(footballCombinationVO);

        }
        return footballCombinationVOList;
    }

    /**
     * 获取一场比赛选取的所有选项
     *
     * @param footballMatchDTO 比赛表
     * @return
     */
    public static List<BallCombinationVO> getAllOptions(BeiDanMatchDTO footballMatchDTO) {
        List<BallCombinationVO> footballCombinationVOList = new ArrayList<>();
        footballCombinationVOList.addAll(getOptions(footballMatchDTO, footballMatchDTO.getGoalOddsList()));
        footballCombinationVOList.addAll(getOptions(footballMatchDTO, footballMatchDTO.getLetOddsList(), "let"));
        footballCombinationVOList.addAll(getOptions(footballMatchDTO, footballMatchDTO.getScoreOddsList()));
        footballCombinationVOList.addAll(getOptions(footballMatchDTO, footballMatchDTO.getHalfWholeOddsList()));
        footballCombinationVOList.addAll(getOptions(footballMatchDTO, footballMatchDTO.getOddEvenOdds()));
        return footballCombinationVOList;
    }

    /**
     * 计算每一注奖金并将List<List<FootballCombinationVO>>返回为List<FootballOptimizationVO>
     *
     * @param footballOptimization 一重list表示注数 二重表示每一注有哪些选项
     * @return
     */
    public static List<BallOptimizationVO> getFootballOptimizationVOlist(List<List<BallCombinationVO>> footballOptimization, Integer multiple) {
        List<BallOptimizationVO> footballOptimizationVOList = new ArrayList<>();
        for (int i = 0; i < footballOptimization.size(); i++) {
            BallOptimizationVO footballOptimizationVO = new BallOptimizationVO();
            footballOptimizationVO.setBallCombinationList(footballOptimization.get(i));
            footballOptimizationVO.setNotes(multiple);
            footballOptimizationVO.setIsShow(false);
            footballOptimizationVOList.add(footballOptimizationVO);
        }
        return footballOptimizationVOList;

    }


    /**
     * 计算 组 注 预测金额
     *
     * @param beiDanMatchList 选中的比赛列表
     * @param multiple        倍数
     * @param pssTypeList     类型，可以单个，可以多个，例如 二串一 三串一 可以单也可以组合
     * @return
     */


    public static BallCalculationVO calculation(List<BeiDanMatchDTO> beiDanMatchList, Integer multiple, List<Integer> pssTypeList) {
        BallCalculationVO beiDanCalculation = new BallCalculationVO();
        beiDanMatchDTOall[] beiDanMatchDTOall = new beiDanMatchDTOall[beiDanMatchList.size()];
        for (int i = 0; i < beiDanMatchList.size(); i++) {
            beiDanMatchDTOall[i] = new beiDanMatchDTOall(beiDanMatchList.get(i));
        }
        double[] range = new double[2];
        int[][] Indexgroup = getIndexgroup(beiDanMatchList.size(), pssTypeList.get(0));
        int betsnum = getallbetsnum(beiDanMatchDTOall, Indexgroup);
        range = getallrange(beiDanMatchDTOall, Indexgroup);
        double allmax = range[0];
        double allmin = range[1];

        List<List<BallCombinationVO>> basketballOptimizationz = getallfootballOptimization(beiDanMatchList, Indexgroup);

        for (int i = 1; i < pssTypeList.size(); i++) {
            int[][] Indexgroup1 = getIndexgroup(beiDanMatchList.size(), pssTypeList.get(i));
            betsnum = betsnum + getallbetsnum(beiDanMatchDTOall, Indexgroup1);
            range = getallrange(beiDanMatchDTOall, Indexgroup1);
            allmax = range[0] + allmax;
            if (allmin > range[1]) {
                allmin = range[1];
            }
            basketballOptimizationz.addAll(getallfootballOptimization(beiDanMatchList, Indexgroup1));
        }
        beiDanCalculation.setNotes(betsnum);
        beiDanCalculation.setMaxPrice(NumberUtil.round(allmax * 2 * multiple * 0.65, 2));
        beiDanCalculation.setMinPrice(NumberUtil.round(allmin * 2 * multiple * 0.65, 2));
        List<BallOptimizationVO>[] FootballOptimizationVOlist = BasketballUtil.getFootballOptimizationVOlist(basketballOptimizationz, multiple);
        FootballOptimizationVOlist[0] = FootballOptimizationVOlist[0].stream().sorted(Comparator.comparing(BallOptimizationVO::getType).thenComparing(BallOptimizationVO::getNotes)).collect(Collectors.toList());
        FootballOptimizationVOlist[1] = FootballOptimizationVOlist[1].stream().sorted(Comparator.comparing(BallOptimizationVO::getType).thenComparing(BallOptimizationVO::getNotes)).collect(Collectors.toList());
        FootballOptimizationVOlist[2] = FootballOptimizationVOlist[2].stream().sorted(Comparator.comparing(BallOptimizationVO::getType).thenComparing(BallOptimizationVO::getNotes)).collect(Collectors.toList());

        beiDanCalculation.setAverageOptimizationList(FootballOptimizationVOlist[0]);
        beiDanCalculation.setColdOptimizationList(FootballOptimizationVOlist[1]);
        beiDanCalculation.setHeatOptimizationList(FootballOptimizationVOlist[2]);

        return beiDanCalculation;
    }

    static class beiDanMatchDTOall {
        private Integer id;
        private String number;
        private String match;
        private int length;
        private Double maxodds;
        private Double minodds;
        private String homeTeam;
        private String visitingTeam;
        private String letBall;

        private void setMaxodds(List<Double> OddsList) {
            Double odds = OddsList.get(0);
            for (int i = 1; i < OddsList.size(); i++) {
                Double z = OddsList.get(i);
                if (z > odds) {
                    odds = z;
                }
            }
            this.maxodds = odds;
        }

        private void setMinodds(List<Double> OddsList) {
            Double odds = OddsList.get(0);
            for (int i = 1; i < OddsList.size(); i++) {
                Double z = OddsList.get(i);
                if (z < odds) {
                    odds = z;
                }
            }
            this.minodds = odds;
        }

        public List<Double> getoddsinallodds(List<Map<String, Object>> GoalOddsList, List<Map<String, Object>> LetOddsList, List<Map<String, Object>> HalfWholeOddsList, List<Map<String, Object>> OddEvenOdds, List<Map<String, Object>> ScoreOddsList) {
            List<Map<String, Object>>[] list = new ArrayList[5];
            list[0] = GoalOddsList;
            list[1] = LetOddsList;
            list[2] = HalfWholeOddsList;
            list[3] = OddEvenOdds;
            list[4] = ScoreOddsList;
            List<Map<String, Object>> notNullodds = getNotNullodds(list);
            return getoddsintype(notNullodds);
        }

        public List<Map<String, Object>> getNotNullodds(List<Map<String, Object>>[] OddsList) {
            for (int i = 0; i < OddsList.length; i++) {
                if (OddsList[i].size() != 0) {
                    return OddsList[i];
                }
            }
            return OddsList[0];
        }

        public List<Double> getoddsintype(List<Map<String, Object>> oddlist) {
            List<Double> reust = new ArrayList<>();
            if (!oddlist.isEmpty()) {
                for (int i = 0; i < oddlist.size(); i++) {
                    reust.add(Double.parseDouble(oddlist.get(i).get("odds").toString()));
                }
            }
            return reust;
        }

        public beiDanMatchDTOall(BeiDanMatchDTO beiDanMatchDTO) {
            this.id = beiDanMatchDTO.getId();
            this.number = beiDanMatchDTO.getNumber();
            this.match = beiDanMatchDTO.getMatch();
            this.homeTeam = beiDanMatchDTO.getHomeTeam();
            this.visitingTeam = beiDanMatchDTO.getVisitingTeam();
            this.length = beiDanMatchDTO.getGoalOddsList().size() + beiDanMatchDTO.getLetOddsList().size() + beiDanMatchDTO.getHalfWholeOddsList().size() + beiDanMatchDTO.getOddEvenOdds().size() + beiDanMatchDTO.getScoreOddsList().size();
            this.letBall = beiDanMatchDTO.getLetBall();
            List<Double> OddsList = new ArrayList<>();
            OddsList = getoddsinallodds(beiDanMatchDTO.getGoalOddsList(), beiDanMatchDTO.getLetOddsList(), beiDanMatchDTO.getHalfWholeOddsList(), beiDanMatchDTO.getOddEvenOdds(), beiDanMatchDTO.getScoreOddsList());
            setMaxodds(OddsList);
            setMinodds(OddsList);
        }
    }

    /**
     * 出奖结果计算
     *
     * @param beiDanMatchList 用户选中的数组
     * @param multiple        倍数
     * @param pssTypeList     过关类型
     * @param str             开奖结果固定形式
     *                        第一场开奖结果： 负,3,0:3,上单,平-负
     *                        第二场开奖结果： 平,2,1:1,下双,平-平
     *                        第三场开奖结果： 平,5,3:2,上单,平-胜
     * @param bonusOddsList   出奖赔率 跟开奖结果一一对一
     * @return 用户中了多少注把中的每一注金额相加在进行返回 (这里计算出来的每一注要乘65%的税)
     */
    public static Double award(List<BeiDanMatchDTO> beiDanMatchList, Integer multiple, List<Integer> pssTypeList, List<String> str, List<String> bonusOddsList) {
        Double res = 0.0;
        String[][] AllFootconse = new String[str.size()][4];
        List<List<Map<String, String>>> hasFootconse = new ArrayList<>();
        for (int i = 0; i < str.size(); i++) {
            String[] eachid = new String[5];
            AllFootconse[i] = str.get(i).split(",");
            if (AllFootconse[i][2].contains(":")) {
                String[] bif = AllFootconse[i][2].split(":");
                Double offset = Double.valueOf(beiDanMatchList.get(i).getLetBall().trim());
                if (offset + Integer.valueOf(bif[0]) > Integer.valueOf(bif[1])) {
                    AllFootconse[i][0] = "胜";
                } else if (offset + Integer.valueOf(bif[0]) < Integer.valueOf(bif[1])) {
                    AllFootconse[i][0] = "负";
                } else if (offset + Integer.valueOf(bif[0]) == Integer.valueOf(bif[1])) {
                    AllFootconse[i][0] = "平";
                }
            }
            String[] oddlist = bonusOddsList.get(i).split(",");

            eachid[0] = Findoddsbytype(beiDanMatchList.get(i).getLetOddsList(), AllFootconse[i][0], oddlist[0]);
            eachid[1] = Findoddsbytype(beiDanMatchList.get(i).getGoalOddsList(), AllFootconse[i][1], oddlist[1]);
            eachid[2] = Findoddsbytype(beiDanMatchList.get(i).getScoreOddsList(), AllFootconse[i][2], oddlist[2]);
            eachid[3] = Findoddsbytype(beiDanMatchList.get(i).getOddEvenOdds(), AllFootconse[i][3], oddlist[3]);
            eachid[4] = Findoddsbytype(beiDanMatchList.get(i).getHalfWholeOddsList(), AllFootconse[i][4], oddlist[4]);
            if ((eachid[0] + eachid[1] + eachid[2] + eachid[3] + eachid[4]).equals("")) {
                str.remove(i);
                beiDanMatchList.remove(i);
                i--;
            } else {
                List<Map<String, String>> realFoot = new ArrayList<>();
                for (int j = 0; j < eachid.length; j++) {
                    if (!eachid[j].equals("")) {
                        Map<String, String> x = new HashMap();
                        x.put("odds", eachid[j]);
                        realFoot.add(x);
                    }
                }
                hasFootconse.add(realFoot);
            }
        }
        for (int i = 0; i < pssTypeList.size(); i++) {
            if (pssTypeList.get(i) > str.size()) {
                pssTypeList.remove(i--);
            }
        }
        if (pssTypeList.isEmpty()) {
            return 0.0;
        }
        List<Map<String, String>>[] realFoot = new ArrayList[beiDanMatchList.size()];
        for (int i = 0; i < beiDanMatchList.size(); i++) {
            realFoot[i] = hasFootconse.get(i);
        }
        for (int i = 0; i < pssTypeList.size(); i++) {
            int[][] Indexgroup = getIndexgroup(beiDanMatchList.size(), pssTypeList.get(i));
            List<List<Map<String, String>>> realallop = getallop(realFoot, Indexgroup);
            res = res + getAllbonus(realallop);
        }
        return res * multiple * 2 * 0.65;
    }

    public static String Findoddsbytype(List<Map<String, Object>> chose, String realoutcome, String odds) {
        String res = "";
        for (int i = 0; i < chose.size(); i++) {
            if (chose.get(i).get("describe").toString().equals(realoutcome))
                res = odds;
        }
        return res;
    }

    public static Double getAllbonus(List<List<Map<String, String>>> realallop) {
        Double res = 0.0;
        for (int i = 0; i < realallop.size(); i++) {
            Double z = 1.0;
            for (int j = 0; j < realallop.get(i).size(); j++) {
                z = z * Double.parseDouble(realallop.get(i).get(j).get("odds"));
            }
            res = res + z;
        }
        return res;
    }

    public static List<List<Map<String, String>>> getallop(List<Map<String, String>>[] chooseFoot, int[][] index) {
        List<List<Map<String, String>>> foot = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            int[] x = index[i];
            foot.addAll(getfootballOption(chooseFoot, x));
        }
        return foot;
    }

    public static List<List<Map<String, String>>> getfootballOption(List<Map<String, String>>[] chooseFoot, int[] groupingindex) {
        List<Map<String, String>>[] footballMatchDTOlistarry = new ArrayList[groupingindex.length];
        for (int i = 0; i < groupingindex.length; i++) {
            footballMatchDTOlistarry[i] = chooseFoot[groupingindex[i]];
        }
        int m = groupingindex.length;
        List<Map<String, String>> footballCombinationVOList = new ArrayList<>();
        List<List<Map<String, String>>> reust = new ArrayList<>();
        getfootballOption(footballMatchDTOlistarry, m, footballCombinationVOList, reust);
        return reust;
    }

    public static void getfootballOption(List<Map<String, String>>[] footballMatchDTOlistarry, int m, List<Map<String, String>> footballCombinationVOList, List<List<Map<String, String>>> reust) {
        if (m == 0) {
            List<Map<String, String>> listcopy = new ArrayList<>();
            listcopy.addAll(footballCombinationVOList);
            reust.add(listcopy);
            footballCombinationVOList.remove(footballCombinationVOList.size() - 1);
        } else {
            for (int i = 0; i < footballMatchDTOlistarry[m - 1].size(); i++) {
                footballCombinationVOList.add(footballMatchDTOlistarry[m - 1].get(i));
                getfootballOption(footballMatchDTOlistarry, m - 1, footballCombinationVOList, reust);
            }
            if (footballCombinationVOList.isEmpty()) {
                return;
            }
            footballCombinationVOList.remove(footballCombinationVOList.size() - 1);
        }
    }
}

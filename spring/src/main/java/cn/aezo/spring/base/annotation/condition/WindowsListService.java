package cn.aezo.spring.base.annotation.condition;

/**
 * Created by smalle on 2017/6/11.
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}

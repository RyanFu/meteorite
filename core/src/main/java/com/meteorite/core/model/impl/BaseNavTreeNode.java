package com.meteorite.core.model.impl;

import com.meteorite.core.model.INavTreeNode;
import com.meteorite.core.ui.model.View;

/**
 * 导航树节点
 *
 * @author wei_jc
 * @since 1.0.0
 */
public class BaseNavTreeNode extends BaseTreeNode implements INavTreeNode {
    @Override
    public View getView() {
        return null;
    }

    @Override
    public String getPresentableText() {
        return null;
    }
}
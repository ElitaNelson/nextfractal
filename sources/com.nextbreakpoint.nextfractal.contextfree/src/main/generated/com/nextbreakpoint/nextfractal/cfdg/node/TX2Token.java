/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class TX2Token extends Token
{
    public TX2Token()
    {
        super.setText("x2");
    }

    public TX2Token(int line, int pos)
    {
        super.setText("x2");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TX2Token(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTX2Token(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TX2Token text.");
    }
}

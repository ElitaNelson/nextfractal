/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class TYToken extends Token
{
    public TYToken()
    {
        super.setText("y");
    }

    public TYToken(int line, int pos)
    {
        super.setText("y");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TYToken(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTYToken(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TYToken text.");
    }
}

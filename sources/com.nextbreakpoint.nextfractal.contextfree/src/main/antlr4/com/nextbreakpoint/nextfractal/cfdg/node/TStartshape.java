/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class TStartshape extends Token
{
    public TStartshape()
    {
        super.setText("startshape");
    }

    public TStartshape(int line, int pos)
    {
        super.setText("startshape");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TStartshape(getLine(), getPos());
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseTStartshape(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TStartshape text.");
    }
}
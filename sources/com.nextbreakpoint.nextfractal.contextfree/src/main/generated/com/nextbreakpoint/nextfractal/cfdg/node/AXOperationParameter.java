/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class AXOperationParameter extends POperationParameter
{
    private TXToken _xToken_;
    private PExpression _expression_;

    public AXOperationParameter()
    {
        // Constructor
    }

    public AXOperationParameter(
        @SuppressWarnings("hiding") TXToken _xToken_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setXToken(_xToken_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new AXOperationParameter(
            cloneNode(this._xToken_),
            cloneNode(this._expression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAXOperationParameter(this);
    }

    public TXToken getXToken()
    {
        return this._xToken_;
    }

    public void setXToken(TXToken node)
    {
        if(this._xToken_ != null)
        {
            this._xToken_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._xToken_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._xToken_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._xToken_ == child)
        {
            this._xToken_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._xToken_ == oldChild)
        {
            setXToken((TXToken) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

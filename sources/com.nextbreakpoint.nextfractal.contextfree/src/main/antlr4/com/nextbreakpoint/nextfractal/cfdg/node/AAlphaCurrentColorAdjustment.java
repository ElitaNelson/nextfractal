/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class AAlphaCurrentColorAdjustment extends PCurrentColorAdjustment
{
    private TAlphaToken _alphaToken_;
    private PExpression _expression_;
    private TBar _bar_;

    public AAlphaCurrentColorAdjustment()
    {
        // Constructor
    }

    public AAlphaCurrentColorAdjustment(
        @SuppressWarnings("hiding") TAlphaToken _alphaToken_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") TBar _bar_)
    {
        // Constructor
        setAlphaToken(_alphaToken_);

        setExpression(_expression_);

        setBar(_bar_);

    }

    @Override
    public Object clone()
    {
        return new AAlphaCurrentColorAdjustment(
            cloneNode(this._alphaToken_),
            cloneNode(this._expression_),
            cloneNode(this._bar_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAlphaCurrentColorAdjustment(this);
    }

    public TAlphaToken getAlphaToken()
    {
        return this._alphaToken_;
    }

    public void setAlphaToken(TAlphaToken node)
    {
        if(this._alphaToken_ != null)
        {
            this._alphaToken_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._alphaToken_ = node;
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

    public TBar getBar()
    {
        return this._bar_;
    }

    public void setBar(TBar node)
    {
        if(this._bar_ != null)
        {
            this._bar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._bar_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._alphaToken_)
            + toString(this._expression_)
            + toString(this._bar_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._alphaToken_ == child)
        {
            this._alphaToken_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        if(this._bar_ == child)
        {
            this._bar_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._alphaToken_ == oldChild)
        {
            setAlphaToken((TAlphaToken) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        if(this._bar_ == oldChild)
        {
            setBar((TBar) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
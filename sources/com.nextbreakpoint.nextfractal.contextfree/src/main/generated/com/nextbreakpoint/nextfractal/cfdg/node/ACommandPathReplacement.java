/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class ACommandPathReplacement extends PPathReplacement
{
    private PPathCommand _pathCommand_;

    public ACommandPathReplacement()
    {
        // Constructor
    }

    public ACommandPathReplacement(
        @SuppressWarnings("hiding") PPathCommand _pathCommand_)
    {
        // Constructor
        setPathCommand(_pathCommand_);

    }

    @Override
    public Object clone()
    {
        return new ACommandPathReplacement(
            cloneNode(this._pathCommand_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACommandPathReplacement(this);
    }

    public PPathCommand getPathCommand()
    {
        return this._pathCommand_;
    }

    public void setPathCommand(PPathCommand node)
    {
        if(this._pathCommand_ != null)
        {
            this._pathCommand_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pathCommand_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._pathCommand_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._pathCommand_ == child)
        {
            this._pathCommand_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._pathCommand_ == oldChild)
        {
            setPathCommand((PPathCommand) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

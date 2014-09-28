/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import java.util.*;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class AOrderedShapeReplacement extends PShapeReplacement
{
    private TString _string_;
    private TLCbkt _lCbkt_;
    private final LinkedList<PShapeAdjustment> _shapeAdjustment_ = new LinkedList<PShapeAdjustment>();
    private TRCbkt _rCbkt_;

    public AOrderedShapeReplacement()
    {
        // Constructor
    }

    public AOrderedShapeReplacement(
        @SuppressWarnings("hiding") TString _string_,
        @SuppressWarnings("hiding") TLCbkt _lCbkt_,
        @SuppressWarnings("hiding") List<PShapeAdjustment> _shapeAdjustment_,
        @SuppressWarnings("hiding") TRCbkt _rCbkt_)
    {
        // Constructor
        setString(_string_);

        setLCbkt(_lCbkt_);

        setShapeAdjustment(_shapeAdjustment_);

        setRCbkt(_rCbkt_);

    }

    @Override
    public Object clone()
    {
        return new AOrderedShapeReplacement(
            cloneNode(this._string_),
            cloneNode(this._lCbkt_),
            cloneList(this._shapeAdjustment_),
            cloneNode(this._rCbkt_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrderedShapeReplacement(this);
    }

    public TString getString()
    {
        return this._string_;
    }

    public void setString(TString node)
    {
        if(this._string_ != null)
        {
            this._string_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._string_ = node;
    }

    public TLCbkt getLCbkt()
    {
        return this._lCbkt_;
    }

    public void setLCbkt(TLCbkt node)
    {
        if(this._lCbkt_ != null)
        {
            this._lCbkt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lCbkt_ = node;
    }

    public LinkedList<PShapeAdjustment> getShapeAdjustment()
    {
        return this._shapeAdjustment_;
    }

    public void setShapeAdjustment(List<PShapeAdjustment> list)
    {
        this._shapeAdjustment_.clear();
        this._shapeAdjustment_.addAll(list);
        for(PShapeAdjustment e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public TRCbkt getRCbkt()
    {
        return this._rCbkt_;
    }

    public void setRCbkt(TRCbkt node)
    {
        if(this._rCbkt_ != null)
        {
            this._rCbkt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rCbkt_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._string_)
            + toString(this._lCbkt_)
            + toString(this._shapeAdjustment_)
            + toString(this._rCbkt_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._string_ == child)
        {
            this._string_ = null;
            return;
        }

        if(this._lCbkt_ == child)
        {
            this._lCbkt_ = null;
            return;
        }

        if(this._shapeAdjustment_.remove(child))
        {
            return;
        }

        if(this._rCbkt_ == child)
        {
            this._rCbkt_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._string_ == oldChild)
        {
            setString((TString) newChild);
            return;
        }

        if(this._lCbkt_ == oldChild)
        {
            setLCbkt((TLCbkt) newChild);
            return;
        }

        for(ListIterator<PShapeAdjustment> i = this._shapeAdjustment_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PShapeAdjustment) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._rCbkt_ == oldChild)
        {
            setRCbkt((TRCbkt) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

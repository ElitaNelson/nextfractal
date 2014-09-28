/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import java.util.*;

import com.nextbreakpoint.nextfractal.cfdg.analysis.*;

@SuppressWarnings("nls")
public final class AOrderedShapeReplacementDeclaration extends PShapeReplacementDeclaration
{
    private TNumber _number_;
    private TStar _star_;
    private TLCbkt _lCbkt_;
    private final LinkedList<PShapeAdjustment> _shapeAdjustment_ = new LinkedList<PShapeAdjustment>();
    private TRCbkt _rCbkt_;
    private PShapeReplacementBlock _shapeReplacementBlock_;

    public AOrderedShapeReplacementDeclaration()
    {
        // Constructor
    }

    public AOrderedShapeReplacementDeclaration(
        @SuppressWarnings("hiding") TNumber _number_,
        @SuppressWarnings("hiding") TStar _star_,
        @SuppressWarnings("hiding") TLCbkt _lCbkt_,
        @SuppressWarnings("hiding") List<PShapeAdjustment> _shapeAdjustment_,
        @SuppressWarnings("hiding") TRCbkt _rCbkt_,
        @SuppressWarnings("hiding") PShapeReplacementBlock _shapeReplacementBlock_)
    {
        // Constructor
        setNumber(_number_);

        setStar(_star_);

        setLCbkt(_lCbkt_);

        setShapeAdjustment(_shapeAdjustment_);

        setRCbkt(_rCbkt_);

        setShapeReplacementBlock(_shapeReplacementBlock_);

    }

    @Override
    public Object clone()
    {
        return new AOrderedShapeReplacementDeclaration(
            cloneNode(this._number_),
            cloneNode(this._star_),
            cloneNode(this._lCbkt_),
            cloneList(this._shapeAdjustment_),
            cloneNode(this._rCbkt_),
            cloneNode(this._shapeReplacementBlock_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrderedShapeReplacementDeclaration(this);
    }

    public TNumber getNumber()
    {
        return this._number_;
    }

    public void setNumber(TNumber node)
    {
        if(this._number_ != null)
        {
            this._number_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._number_ = node;
    }

    public TStar getStar()
    {
        return this._star_;
    }

    public void setStar(TStar node)
    {
        if(this._star_ != null)
        {
            this._star_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._star_ = node;
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

    public PShapeReplacementBlock getShapeReplacementBlock()
    {
        return this._shapeReplacementBlock_;
    }

    public void setShapeReplacementBlock(PShapeReplacementBlock node)
    {
        if(this._shapeReplacementBlock_ != null)
        {
            this._shapeReplacementBlock_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._shapeReplacementBlock_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._number_)
            + toString(this._star_)
            + toString(this._lCbkt_)
            + toString(this._shapeAdjustment_)
            + toString(this._rCbkt_)
            + toString(this._shapeReplacementBlock_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        if(this._star_ == child)
        {
            this._star_ = null;
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

        if(this._shapeReplacementBlock_ == child)
        {
            this._shapeReplacementBlock_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        if(this._star_ == oldChild)
        {
            setStar((TStar) newChild);
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

        if(this._shapeReplacementBlock_ == oldChild)
        {
            setShapeReplacementBlock((PShapeReplacementBlock) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

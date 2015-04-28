<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-04-27 15:49:39</timestamp>
    <julia>true</julia>
    <point>-0.7433333333333333</point>
    <point>0.08</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-2.5 - 1.5i,+0.5 + 1.5i] [z,n] {
		trap trap1 [&lt;-1,0&gt;] {
			MOVETO(&lt;-3.0,+0.45&gt;);
			LINETO(&lt;-3.0,+0.45&gt;);
			LINETO(&lt;+3.0,+0.45&gt;);
			LINETO(&lt;+3.0,-0.45&gt;);
			LINETO(&lt;-3.0,-0.45&gt;);
		}		
		begin {
			z = x;
		}
		loop [0, 500] (trap1 ~? z) {
			z = z * z + w;
		}
	}
	color [#FF000000] {
		rule (re(n) = 0) [1.0] {
			1,0,0,0
		}
		rule (re(n) &gt; 0) [1.0] {
			1,1,1,1
		}
	}
}
</source>
    <time>0.0</time>
    <traslation>0.0</traslation>
    <traslation>0.0</traslation>
    <traslation>1.0</traslation>
    <traslation>0.0</traslation>
</mandelbrot>
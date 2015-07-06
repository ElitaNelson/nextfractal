<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-04-28 18:56:15</timestamp>
    <julia>true</julia>
    <point>0.42630508069430717</point>
    <point>0.2536917043616403</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [&lt;-1.5,-1.5&gt;,&lt;1.5,1.5&gt;] [x,n] {
		loop [0, 200] (mod2(x) &gt; 4) {
			x = x * x + w;
			if (re(x) &gt; 1) {
				x = 2;
			}
		}
	}
	color [(1,0,0,0)] {
		rule (n = 0) [1.0] {
			1,0,0,0
		}
		rule (n &gt; 0) [1.0] {
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
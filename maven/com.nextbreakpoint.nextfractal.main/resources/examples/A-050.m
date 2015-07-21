<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<mandelbrot>
    <timestamp>2015-07-21 08:58:35</timestamp>
    <julia>false</julia>
    <point>0.0</point>
    <point>0.0</point>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <rotation>0.0</rotation>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <scale>1.0</scale>
    <source>fractal {
	orbit [-3.0 - 1.5i,+0.0 + 1.5i] [x,n] {
		loop [0, 200] (mod2(x) &gt; 40) {
			x = x * x + w;
		}
	}
	color [#FFF0F0F0] {
		rule (n = 0) [1] {
			1,
			(1 + sin(&lt;x&gt; * pi * 10)) / 2,
			(1 + sin(&lt;x&gt; * pi * 10)) / 2,
			(1 + sin(&lt;x&gt; * pi * 10)) / 2
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

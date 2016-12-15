# Pixart
Creating abstract art from graphs

## Installing Pixart
Git clone Pixart
<pre><code>$ git clone https://github.com/olinminions/Pixart.git</code></pre>

## Running Pixart
Run the run.sh script in terminal 
<pre><code>$ bash ./run.sh</code></pre>

## GUI
![GUI](https://github.com/olinminions/Pixart/blob/master/images/gui.png "GUI")

## User Interaction
* **Height**: The number of rows of pixels. Enter a number between 10 and 1000. Press ‘Enter’ after you type in your desired number.
* Width: The number of columns of pixels. Enter a number between 10 and 1000. Press ‘Enter’ after you type in your desired number.
* **Complexity**: Decides how complex you want the image to be. This actually correspond to the number of vertices in the graph data structure used to create the Pixart image. Enter a number between 0 and 100. Press ‘Enter’ after you type in your desired number.
* **Variation**: Decides how much variation in the color the Pixart image has, or in other words, how likely will there be darker colors and variation in the colors. This correspond to the number of edges we remove in the graph data structure used to create the Pixart image. Enter a number between the range in parenthesis (this is dependent on complexity). Press ‘Enter’ after you type in your desired number.
* **Color Scheme**: Set the general color scheme of the art, given a choice of 8 pre-determined color schemes. You can only select one color scheme at a time. 
	* Red
	* Blue
	* Green
	* Grey
	* Purple
	* Yellow
	* Cyan
	* Pastel
* **Blur**: How blurred the pixart image will be. Note that you can’t generate an image with blur and unblur it, or generate an image without blur and blurs it. The image will only update when you click Generate Graph. 
	* No blur
	* Light blur (blur once)
	* heavy blur (blur twice)
* **Generate Graph**: The button that generates the art and updates the screen.
* **Quit**: Exit the program and closes the window.
## UML
![UML](https://github.com/olinminions/Pixart/blob/master/images/UML.png "UML")

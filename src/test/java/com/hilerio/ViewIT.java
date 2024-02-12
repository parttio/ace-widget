package com.hilerio;

import com.vaadin.flow.component.grid.testbench.GridElement;
import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

    @Test
    public void componentWorks() {
        // Select the first line on grid
        final GridElement grid = (GridElement) $(GridElement.class).first();
        grid.getRow(0).doubleClick();

        final TestBenchElement paperSlider = $("ace-widget").first();
        // Check that ace-widget contains at least one other element, which means that
        // is has been upgraded to a custom element and not just rendered as an empty
        // tag
        Assert.assertTrue(
                paperSlider.$(TestBenchElement.class).all().size() > 0);
    }
}

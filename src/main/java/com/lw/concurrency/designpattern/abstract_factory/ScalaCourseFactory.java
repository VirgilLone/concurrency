package com.lw.concurrency.designpattern.abstract_factory;

public class ScalaCourseFactory extends CourseFactory {
    @Override
    protected INote createNote() {
        super.init();
        return new ScalaNote();
    }

    @Override
    protected IVideo createVideo() {
        super.init();
        return new ScalaVideo();
    }
}

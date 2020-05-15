package com.brenosalles.handlers;

import com.brenosalles.decorators.IComponent;

public interface IHandler extends IComponent {
    IHandler setNext(IHandler handler);
}
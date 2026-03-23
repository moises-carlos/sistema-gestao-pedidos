package com.moises.pedidos.core;

import java.util.List;

public interface CrudService<T>{

     void cadastrar(T entidade) throws Exception;
     List<T> listarTodos();
}

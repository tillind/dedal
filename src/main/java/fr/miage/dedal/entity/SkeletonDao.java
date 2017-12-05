/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.dedal.entity;

/**
 *
 * @author alex
 */
public interface SkeletonDao<T> {
    public T findAll();
    public T Create(T o);
    public T Update(T o);
    public void Delete(T o);
}

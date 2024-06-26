package util.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.codeseg.ReturnParameterCodeSeg;

import static global.General.log;

public interface Iterator {
    /**
     * Iterator interface, used for making loops with conditions
     */


    /**
     * Private timer for internal methods
     */
    Timer time = new Timer();

    /**
     * Method to define the condition to check (make this return true for default behavior)
     * @return true
     */
    boolean condition();

    /**
     * Do something while the condition is true (default behavior)
     * @param code
     */
    default void whileActive(CodeSeg code){
        boolean oldShouldUpdate = log.getShouldUpdateOnShow();
        log.setShouldUpdateOnShow(false);
        while (condition()){
            code.run();
            log.showTelemetry();
        }
        log.setShouldUpdateOnShow(oldShouldUpdate);
    }

    /**
     * Do something while the condition is true and the active condition is true
     * @param code
     */
    default void whileActive(ReturnCodeSeg<Boolean> active, CodeSeg code){
        boolean oldShouldUpdate = log.getShouldUpdateOnShow();
        log.setShouldUpdateOnShow(false);
        while (condition() && active.run()){
            code.run();
            log.showTelemetry();
        }
        log.setShouldUpdateOnShow(oldShouldUpdate);
    }

    /**
     * Do something for some amount of time
     * @param code
     * @param secs
     */
    default void whileTime(CodeSeg code, double secs){
        boolean oldShouldUpdate = log.getShouldUpdateOnShow();
        log.setShouldUpdateOnShow(false);
        time.reset();
        while (condition() && time.seconds() < secs){
            code.run();
            log.showTelemetry();
        }
        log.setShouldUpdateOnShow(oldShouldUpdate);
    }

    /**
     * Pause for some amount of seconds
     * @param secs
     */
    default void pause(double secs){ whileTime(() -> {}, secs); }


    /**
     * Static helper methods for looping through lists
     * @param list
     * @param code
     * @param <T>
     */
    static<T> void forAll(ArrayList<T> list, ParameterCodeSeg<T> code){
        for(T obj: list){
            code.run(obj);
        }
    }

    static<T> void forAll(Collection<T> list, ParameterCodeSeg<T> code){
        for(T obj: list){
            code.run(obj);
        }
    }

    static<T> void forAll(TreeMap<?, T> list, ParameterCodeSeg<T> code){
        for(T obj: list.values()){
            code.run(obj);
        }
    }

    static<T> void forAll(T[] list, ParameterCodeSeg<T> code){
        for(T obj: list){
            code.run(obj);
        }
    }

    static <T> int forAllCount(ArrayList<T> list, ReturnParameterCodeSeg<T, Boolean> code){
        int count = 0;
        for(T obj: list){
            count += code.run(obj) ? 1:0;
        }
        return count;
    }

    static <T> boolean forAllCondition(ArrayList<T> list, ReturnParameterCodeSeg<T, Boolean> code){
        for(T obj: list){
            if(code.run(obj)){
                return true;
            }
        }
        return false;
    }
}

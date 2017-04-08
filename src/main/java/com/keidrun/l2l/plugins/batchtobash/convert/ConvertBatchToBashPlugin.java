/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.convert;

import java.util.ArrayList;
import java.util.List;

import com.keidrun.l2l.face.element.Combination;
import com.keidrun.l2l.face.element.Language;
import com.keidrun.l2l.face.plugins.ConvertPlugin;
import com.keidrun.l2l.plugins.batchtobash.converters.CommandConverter;
import com.keidrun.l2l.plugins.batchtobash.converters.Converter;
import com.keidrun.l2l.plugins.batchtobash.converters.GrammarConverter;

/**
 * Batch to bash conversion.
 * 
 * @author Keid
 */
public class ConvertBatchToBashPlugin implements ConvertPlugin {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.keidrun.l2l.face.plugin.ConvertPlugin#isSupported(com.keidrun.l2l.
     * face.element.Combination)
     */
    @Override
    public boolean isSupported(Combination combination) {

        if (combination.origin().equals(Language.BATCH) && combination.dest().equals(Language.BASH)) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.keidrun.l2l.face.plugin.ConvertPlugin#convert(com.keidrun.l2l.face.
     * element.Combination, java.lang.String)
     */
    @Override
    public String convert(Combination combination, String program) {

        if (!isSupported(combination)) {
            throw new IllegalArgumentException(
                    "The combination of " + combination.origin() + " and " + combination.dest() + "is not supported.");
        }

        List<Converter> converters = new ArrayList<Converter>();
        converters.add(new GrammarConverter());
        converters.add(new CommandConverter());
        for (Converter c : converters) {
            program = c.convert(program);
        }

        return program;

    }

}

package fr.kysio.phonemod.sqript;

import fr.kysio.phonemod.api.applications.Application;
import fr.nico.sqript.meta.Type;
import fr.nico.sqript.structures.ScriptElement;
import fr.nico.sqript.types.ScriptType;

import javax.annotation.Nullable;

@Type(name = "player", parsableAs = {})
public class TypeApplication extends ScriptType<Application> {


    public TypeApplication(Application object) {
        super(object);
    }

    @Nullable
    @Override
    public ScriptElement parse(String s) {
        return null;
    }
}

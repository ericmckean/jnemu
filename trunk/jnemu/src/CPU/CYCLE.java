package CPU;

public class CYCLE
{
    private static int cycle[];

    public static void init()
    {
        cycle = new int[0x100];
        //TODO: add cycle value per opcode...
        cycle[0x00] = 0; cycle[0x40] = 0; cycle[0x80] = 0; cycle[0xC0] = 0;
        cycle[0x01] = 0; cycle[0x41] = 0; cycle[0x81] = 0; cycle[0xC1] = 0;
        cycle[0x02] = 0; cycle[0x42] = 0; cycle[0x82] = 0; cycle[0xC2] = 0;
        cycle[0x03] = 0; cycle[0x43] = 0; cycle[0x83] = 0; cycle[0xC3] = 0;
        cycle[0x04] = 0; cycle[0x44] = 0; cycle[0x84] = 0; cycle[0xC4] = 0;
        cycle[0x05] = 0; cycle[0x45] = 0; cycle[0x85] = 0; cycle[0xC5] = 0;
        cycle[0x06] = 0; cycle[0x46] = 0; cycle[0x86] = 0; cycle[0xC6] = 0;
        cycle[0x07] = 0; cycle[0x47] = 0; cycle[0x87] = 0; cycle[0xC7] = 0;
        cycle[0x08] = 0; cycle[0x48] = 0; cycle[0x88] = 0; cycle[0xC8] = 0;
        cycle[0x09] = 0; cycle[0x49] = 0; cycle[0x89] = 0; cycle[0xC9] = 0;
        cycle[0x0A] = 0; cycle[0x4A] = 0; cycle[0x8A] = 0; cycle[0xCA] = 0;
        cycle[0x0B] = 0; cycle[0x4B] = 0; cycle[0x8B] = 0; cycle[0xCB] = 0;
        cycle[0x0C] = 0; cycle[0x4C] = 0; cycle[0x8C] = 0; cycle[0xCC] = 0;
        cycle[0x0D] = 0; cycle[0x4D] = 0; cycle[0x8D] = 0; cycle[0xCD] = 0;
        cycle[0x0E] = 0; cycle[0x4E] = 0; cycle[0x8E] = 0; cycle[0xCE] = 0;
        cycle[0x0F] = 0; cycle[0x4F] = 0; cycle[0x8F] = 0; cycle[0xCF] = 0;
        cycle[0x10] = 0; cycle[0x50] = 0; cycle[0x90] = 0; cycle[0xD0] = 0;
        cycle[0x11] = 0; cycle[0x51] = 0; cycle[0x91] = 0; cycle[0xD1] = 0;
        cycle[0x12] = 0; cycle[0x52] = 0; cycle[0x92] = 0; cycle[0xD2] = 0;
        cycle[0x13] = 0; cycle[0x53] = 0; cycle[0x93] = 0; cycle[0xD3] = 0;
        cycle[0x14] = 0; cycle[0x54] = 0; cycle[0x94] = 0; cycle[0xD4] = 0;
        cycle[0x15] = 0; cycle[0x55] = 0; cycle[0x95] = 0; cycle[0xD5] = 0;
        cycle[0x16] = 0; cycle[0x56] = 0; cycle[0x96] = 0; cycle[0xD6] = 0;
        cycle[0x17] = 0; cycle[0x57] = 0; cycle[0x97] = 0; cycle[0xD7] = 0;
        cycle[0x18] = 0; cycle[0x58] = 0; cycle[0x98] = 0; cycle[0xD8] = 0;
        cycle[0x19] = 0; cycle[0x59] = 0; cycle[0x99] = 0; cycle[0xD9] = 0;
        cycle[0x1A] = 0; cycle[0x5A] = 0; cycle[0x9A] = 0; cycle[0xDA] = 0;
        cycle[0x1B] = 0; cycle[0x5B] = 0; cycle[0x9B] = 0; cycle[0xDB] = 0;
        cycle[0x1C] = 0; cycle[0x5C] = 0; cycle[0x9C] = 0; cycle[0xDC] = 0;
        cycle[0x1D] = 0; cycle[0x5D] = 0; cycle[0x9D] = 0; cycle[0xDD] = 0;
        cycle[0x1E] = 0; cycle[0x5E] = 0; cycle[0x9E] = 0; cycle[0xDE] = 0;
        cycle[0x1F] = 0; cycle[0x5F] = 0; cycle[0x9F] = 0; cycle[0xDF] = 0;
        cycle[0x20] = 0; cycle[0x60] = 0; cycle[0xA0] = 0; cycle[0xE0] = 0;
        cycle[0x21] = 0; cycle[0x61] = 0; cycle[0xA1] = 0; cycle[0xE1] = 0;
        cycle[0x22] = 0; cycle[0x62] = 0; cycle[0xA2] = 0; cycle[0xE2] = 0;
        cycle[0x23] = 0; cycle[0x63] = 0; cycle[0xA3] = 0; cycle[0xE3] = 0;
        cycle[0x24] = 0; cycle[0x64] = 0; cycle[0xA4] = 0; cycle[0xE4] = 0;
        cycle[0x25] = 0; cycle[0x65] = 0; cycle[0xA5] = 0; cycle[0xE5] = 0;
        cycle[0x26] = 0; cycle[0x66] = 0; cycle[0xA6] = 0; cycle[0xE6] = 0;
        cycle[0x27] = 0; cycle[0x67] = 0; cycle[0xA7] = 0; cycle[0xE7] = 0;
        cycle[0x28] = 0; cycle[0x68] = 0; cycle[0xA8] = 0; cycle[0xE8] = 0;
        cycle[0x29] = 0; cycle[0x69] = 0; cycle[0xA9] = 0; cycle[0xE9] = 0;
        cycle[0x2A] = 0; cycle[0x6A] = 0; cycle[0xAA] = 0; cycle[0xEA] = 0;
        cycle[0x2B] = 0; cycle[0x6B] = 0; cycle[0xAB] = 0; cycle[0xEB] = 0;
        cycle[0x2C] = 0; cycle[0x6C] = 0; cycle[0xAC] = 0; cycle[0xEC] = 0;
        cycle[0x2D] = 0; cycle[0x6D] = 0; cycle[0xAD] = 0; cycle[0xED] = 0;
        cycle[0x2E] = 0; cycle[0x6E] = 0; cycle[0xAE] = 0; cycle[0xEE] = 0;
        cycle[0x2F] = 0; cycle[0x6F] = 0; cycle[0xAF] = 0; cycle[0xEF] = 0;
        cycle[0x30] = 0; cycle[0x70] = 0; cycle[0xB0] = 0; cycle[0xF0] = 0;
        cycle[0x31] = 0; cycle[0x71] = 0; cycle[0xB1] = 0; cycle[0xF1] = 0;
        cycle[0x32] = 0; cycle[0x72] = 0; cycle[0xB2] = 0; cycle[0xF2] = 0;
        cycle[0x33] = 0; cycle[0x73] = 0; cycle[0xB3] = 0; cycle[0xF3] = 0;
        cycle[0x34] = 0; cycle[0x74] = 0; cycle[0xB4] = 0; cycle[0xF4] = 0;
        cycle[0x35] = 0; cycle[0x75] = 0; cycle[0xB5] = 0; cycle[0xF5] = 0;
        cycle[0x36] = 0; cycle[0x76] = 0; cycle[0xB6] = 0; cycle[0xF6] = 0;
        cycle[0x37] = 0; cycle[0x77] = 0; cycle[0xB7] = 0; cycle[0xF7] = 0;
        cycle[0x38] = 0; cycle[0x78] = 0; cycle[0xB8] = 0; cycle[0xF8] = 0;
        cycle[0x39] = 0; cycle[0x79] = 0; cycle[0xB9] = 0; cycle[0xF9] = 0;
        cycle[0x3A] = 0; cycle[0x7A] = 0; cycle[0xBA] = 0; cycle[0xFA] = 0;
        cycle[0x3B] = 0; cycle[0x7B] = 0; cycle[0xBB] = 0; cycle[0xFB] = 0;
        cycle[0x3C] = 0; cycle[0x7C] = 0; cycle[0xBC] = 0; cycle[0xFC] = 0;
        cycle[0x3D] = 0; cycle[0x7D] = 0; cycle[0xBD] = 0; cycle[0xFD] = 0;
        cycle[0x3E] = 0; cycle[0x7E] = 0; cycle[0xBE] = 0; cycle[0xFE] = 0;
        cycle[0x3F] = 0; cycle[0x7F] = 0; cycle[0xBF] = 0; cycle[0xFF] = 0;
    }

    public static int getCycle(int opcode)
    {
        return cycle[opcode];
    }
}
